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

/**
 * Prices class representing arraylist of new prices
 */
public class Prices implements Runnable, LocalSubject<String, Message>
{
  private ArrayList<Price> newPrices;
  private ArrayList<Price> allPrices;

  private StockAPI stockAPI;
  private Gson gson;
  private Timestamp timestampOfCompany;
  private PropertyChangeHandler<String, Message> property;

  private boolean running;

  /**
   * Constructor initialising all instant variables
   */
  public Prices() {
    property = new PropertyChangeHandler<String, Message>(this);
    timestampOfCompany = new Timestamp(0);

    allPrices = new ArrayList<>();
    newPrices = new ArrayList<>();
    stockAPI = new StockAPI();
    gson = new Gson();
    running = true;
  }

  /**
   * gets arraylist of all newest Prices
   * @return arraylist newPrices
   */
  public ArrayList<Price> getNewPrices() {
    return newPrices;
  }

  /**
   * setts boolean running to false
   */
  public void close(){
    running = false;
  }

  /**
   * checks if there is Price object of company with given symbol
   * inside of arraylist of newPrices
   * @param symbol symbol of company to be looked for
   * @return boolean
   */
  public boolean isInside(String symbol){
    for(Price p:newPrices){
      if (p.getSymbol().equals(symbol)){
        return true;
      }
    }
    return false;
  }

  /**
   * returns Price object of company which symbol is given,
   * from newPrices arraylist
   * @param symbol symbol of Price object
   * @return Price
   */
  public Price getBySymbol(String symbol){
    for(Price p:newPrices){
      if (p.getSymbol().equals(symbol)){
        return p;
      }
    }
    return null;
  }

  /**
   * adds Price object into arraylist allPrices
   * @param price Price object
   */
  public void addPrice(Price price){
    allPrices.add(price);
  }

  /**
   * goes threw allPrices arraylist
   * and adds newest ones from them into newPrices arraylist
   */
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

  /**
   * gets newest TradingData from StockInfo
   * of specific company which is given by API
   * and sets TimeStamp timestampOfCompany to time of the TradingData
   * @param stockInfo stockInfo of specific company given by API
   * @return TradingData of company
   */
  public TradingData newestStock(StockInfo stockInfo){
    int result = 0;
    for(int x = 15; x > 0; x--){
      ZonedDateTime got = stockInfo.getTimeSeries().get(x).getDate();
      Timestamp n = Timestamp.from(got.plusDays(1).minusHours(6).toInstant());
      if(n.before(new Timestamp(System.currentTimeMillis()))){
        timestampOfCompany.setTime(n.getTime());
        result = x;
      }
    }
    return stockInfo.getTimeSeries().get(result);
  }

  /**
   * gets by symbol stockInfo of specific company in intervals given from API,
   * after calling API method sleeps for 12 seconds and than returns stockInfo
   * @param symbol symbol of company
   * @param requestType time interval of TradingData inside StockInfo
   * @return StockInfo of company
   * @throws IOException
   * @throws InterruptedException
   */
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

  /**
   * Thread that checks if some Price object of newPrices needs to be updated
   * by checking if hour of the Price(timestampOfCompany) is too old compare to current time
   * and also if the Price is different compare to Price from API.
   *
   * If the Price needs to be updated thread fires propertyChange with new Price to be updated,
   * and updates the Price from old one in newPrice arraylist
   *
   * after checking of all the companies is done thread goes to sleep for 10 minutes
   * after 10 minutes of thread sleeping the checking of Prices starts again
   */
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
