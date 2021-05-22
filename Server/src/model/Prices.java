package model;


import com.google.gson.Gson;
import persistence.CompaniesDatabase;
import persistence.CompaniesPersistence;
import persistence.PriceHistoryDatabase;
import persistence.PriceHistoryPersistence;
import stockAPI.RequestType;
import stockAPI.StockAPI;
import stockAPI.StockInfo;
import stockAPI.TradingData;
import utility.PropertyChangeSubject;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Prices implements Runnable, LocalSubject<String, Message>
{
  ArrayList<Price> newPrices;
  PriceHistoryPersistence priceHistoryPersistence;
  CompaniesPersistence companiesPersistence;
  private StockAPI stockAPI;
  private Gson gson;
  private Timestamp now;
  private Timestamp timestampOfCompany;
  private PropertyChangeHandler<String, Message> property;

  private boolean running;

  public Prices() {
    property = new PropertyChangeHandler<String, Message>(this);
    now = new Timestamp(System.currentTimeMillis());
    timestampOfCompany = new Timestamp(System.currentTimeMillis());
    priceHistoryPersistence = PriceHistoryDatabase.getInstance();
    companiesPersistence = CompaniesDatabase.getInstance();
    newPrices = new ArrayList<>();
    stockAPI = new StockAPI();
    gson = new Gson();
    running = true;
  }

  public ArrayList<Price> getNewPrices() {
    return newPrices;
  }

  public void close(){
    running = false;
  }


  public boolean isInside(String symbol){
    for(Price p:newPrices){
      if (p.getSymbol().equals(symbol)){
        return true;
      }
    }
    return false;
  }

  public Price getBySymbol(String symbol){
    for(Price p:newPrices){
      if (p.getSymbol().equals(symbol)){
        return p;
      }
    }
    return null;
  }

  public void newPrices() {
    newPrices.clear();
    now.setTime(System.currentTimeMillis());
    if (newPrices.isEmpty()) {
      int x = 0;
      try {
        for (Price p : priceHistoryPersistence.load()) {
          if (newPrices.size() < x + 1) {
            newPrices.add(p);
          }
          else if(isInside(p.getSymbol()) && now.getTime() - getBySymbol(p.getSymbol()).getTimestamp().getTime() > now.getTime() - p.getTimestamp().getTime()){
            newPrices.removeIf(PP -> PP.getSymbol().equals(p.getSymbol()));
            newPrices.add(p);
          }
          else if (!isInside(p.getSymbol())) {
            newPrices.add(p);
          }
        }
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public TradingData newestStock(StockInfo stockInfo){
    int result = 0;
    for(int x = 15; x > 0; x--){
      now.setTime(System.currentTimeMillis());
      ZonedDateTime got = stockInfo.getTimeSeries().get(x).getDate();
      Timestamp n = new Timestamp(got.getYear()-1900, got.getMonthValue()-1,
          got.getDayOfMonth() + 1, got.getHour(), got.getMinute(), got.getSecond(),
          got.getNano());
      if(now.getTime()-n.getTime() > 0){
        timestampOfCompany.setTime(n.getTime());
        result = x;
      }
    }
    return stockInfo.getTimeSeries().get(result);
  }

  public StockInfo APIRequest(String symbol, RequestType requestType)
      throws InterruptedException, IOException {
    String json = stockAPI.getStockInfo(symbol, requestType);
    Thread.sleep(12000);
    return gson.fromJson(json, StockInfo.class).convert();
  }

  @Override public void run()
  {
    while(running){
      StockInfo stockInfo = null;

      newPrices();
      for(Price p:newPrices){
        try {
          stockInfo = APIRequest(p.getSymbol(), RequestType.INTRADAY);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        TradingData tradingData = newestStock(stockInfo);
        System.out.println("Checking company " + p.getSymbol());
        if(now.getTime() - p.getTimestamp().getTime() > 1 && p.getTimestamp().getTime() != timestampOfCompany.getTime()){
          try {
            System.out.println("Updating company " + p.getSymbol() + " with price " + tradingData.getClose());
            p.setPrice(tradingData.getClose());
            Company company = new Company(p.getSymbol(),p.getSymbol());
            company.setCurrentPrice(p.getPrice());
            priceHistoryPersistence.save(p.getSymbol(), tradingData, timestampOfCompany);
            companiesPersistence.update(company);
            property.firePropertyChange("Price",p.getSymbol(),new Message(null,p));
          }
          catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      try {
        Thread.sleep(600000);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }



  @Override public boolean addListener(
      GeneralListener<String, Message> listener, String... propertyNames)
  {
    return property.addListener(listener,propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, Message> listener, String... propertyNames)
  {
    return property.removeListener(listener,propertyNames);
  }
}
