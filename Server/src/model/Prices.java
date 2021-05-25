package model;


import com.google.gson.Gson;
import stockAPI.RequestType;
import stockAPI.StockAPI;
import stockAPI.StockInfo;
import stockAPI.TradingData;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Prices implements Runnable, LocalSubject<String, Message>
{
  private ArrayList<Price> newPrices;
  private ArrayList<Price> allPrices;

  private StockAPI stockAPI;
  private Gson gson;
  private Timestamp timestampOfCompany;
  private PropertyChangeHandler<String, Message> property;

  private boolean running;

  public Prices() {
    property = new PropertyChangeHandler<String, Message>(this);
    timestampOfCompany = new Timestamp(0);

    allPrices = new ArrayList<>();
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

  public void addPrice(Price price){
    allPrices.add(price);
  }

  public void newPrices() {
    newPrices.clear();
    for (Price p : allPrices) {
      if (!isInside(p.getSymbol())) {
        newPrices.add(p);
      }
      else if(isInside(p.getSymbol()) && getBySymbol(p.getSymbol()).getTimestamp().before(p.getTimestamp()) ){
        newPrices.removeIf(PP -> PP.getSymbol().equals(p.getSymbol()));
        newPrices.add(p);
      }
    }
  }

  public TradingData newestStock(StockInfo stockInfo){
    int result = 0;
    for(int x = 15; x > 0; x--){
      ZonedDateTime got = stockInfo.getTimeSeries().get(x).getDate();
      Timestamp n = Timestamp.from(got.plusDays(1).toInstant());
      if(n.before(new Timestamp(System.currentTimeMillis()))){
        timestampOfCompany.setTime(n.getTime());
        result = x;
      }
    }
    return stockInfo.getTimeSeries().get(result);
  }

  public StockInfo APIRequest(String symbol, RequestType requestType)
      throws IOException, InterruptedException
  {
    String json = stockAPI.getStockInfo(symbol, requestType);
    try {
      Thread.sleep(12000);
    }
    catch (InterruptedException e) {

    }
    return gson.fromJson(json, StockInfo.class).convert();
  }

  @Override public void run()
  {
    newPrices();
    while(running){
      System.out.println("Prices thread started");
      StockInfo stockInfo = null;

      for(Price p:newPrices){
        if(!running){
          break;
        }
        try {
          stockInfo = APIRequest(p.getSymbol(), RequestType.INTRADAY);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        TradingData tradingData = newestStock(stockInfo);
        System.out.println("Checking company " + p.getSymbol());
        if(p.getTimestamp().before(new Timestamp(System.currentTimeMillis())) && p.getTimestamp().getTime() != timestampOfCompany.getTime()){
          try {
            p.set(new Price(timestampOfCompany, p.getSymbol(), tradingData.getOpen(), tradingData.getLow(), tradingData.getHigh(), tradingData.getClose(), tradingData.getVolume()));
            getBySymbol(p.getSymbol()).set(p);
            System.out.println("Updating company " + p.getSymbol() + " with price " + tradingData.getClose());
            property.firePropertyChange("Price",p.getSymbol(),new Message(null,p));
          }
          catch (Exception e) {
            e.printStackTrace();
          }
        }
      }

      try {
        if (running)
        {
          Thread.sleep(600000);
        }
      }
      catch (InterruptedException e) {

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
