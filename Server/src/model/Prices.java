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
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Prices represents class which updates prices of companies in database
 */
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

  /**
   * Constructor that initializes all instant variables
   */
  public Prices()
  {
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

  /**
   * gets arraylist of newest prices
   *
   * @return newPrices
   */
  public ArrayList<Price> getNewPrices()
  {
    return newPrices;
  }

  public void close()
  {
    running = false;
  }

  /**
   * a method which checks if symbol of company is inside of newest prices
   *
   * @param symbol symbol of company to be checked
   * @return boolean
   */
  public boolean isInside(String symbol)
  {
    for (Price p : newPrices)
    {
      if (p.getSymbol().equals(symbol))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * gets Price object from newest prices by symbol
   *
   * @param symbol symbol of company which Price object represents
   * @return Price object
   */
  public Price getBySymbol(String symbol)
  {
    for (Price p : newPrices)
    {
      if (p.getSymbol().equals(symbol))
      {
        return p;
      }
    }
    return null;
  }

  /**
   * checks newest prices, if there is not price with symbol of newest price adds the price into list if it is it just updates old value
   */
  public void newPrices()
  {
    newPrices.clear();
    now.setTime(System.currentTimeMillis());
    if (newPrices.isEmpty())
    {
      int x = 0;
      try
      {
        for (Price p : priceHistoryPersistence.load())
        {
          if (newPrices.size() < x + 1)
          {
            newPrices.add(p);
          }
          else if (isInside(p.getSymbol()) &&
              now.getTime() - getBySymbol(p.getSymbol()).getTimestamp()
                  .getTime() > now.getTime() - p.getTimestamp().getTime())
          {
            newPrices.removeIf(PP -> PP.getSymbol().equals(p.getSymbol()));
            newPrices.add(p);
          }
          else if (!isInside(p.getSymbol()))
          {
            newPrices.add(p);
          }
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * gets newest stock from all stocks that are threw API given
   *
   * @param stockInfo all the stocks of company
   * @return newest stock
   */
  public TradingData newestStock(StockInfo stockInfo)
  {
    int result = 0;
    for (int x = 15; x > 0; x--)
    {
      now.setTime(System.currentTimeMillis());
      ZonedDateTime got = stockInfo.getTimeSeries().get(x).getDate();
      Timestamp n = new Timestamp(got.getYear() - 1900, got.getMonthValue() - 1,
          got.getDayOfMonth() + 1, got.getHour(), got.getMinute(),
          got.getSecond(), got.getNano());
      if (now.getTime() - n.getTime() > 0)
      {
        timestampOfCompany.setTime(n.getTime());
        result = x;
      }
    }
    return stockInfo.getTimeSeries().get(result);
  }

  /**
   * calls from API all stocks of specific company
   *
   * @param symbol      symbol of company
   * @param requestType variable of time interval
   * @return all stocks of company of given company symbol in given intervals
   * @throws InterruptedException
   * @throws IOException
   */
  public StockInfo APIRequest(String symbol, RequestType requestType)
      throws InterruptedException, IOException
  {
    String json = stockAPI.getStockInfo(symbol, requestType);
    Thread.sleep(12000);
    return gson.fromJson(json, StockInfo.class).convert();
  }

  /**
   * Thread that runs all the time after system is started, it loops threw all newest prices
   * and it checks them if they are up to time with newest ones from api,
   * if they are not newest it will add newest price into database,
   * it will change price of given company and fire property
   */
  @Override public void run()
  {
    while (running)
    {
      StockInfo stockInfo = null;

      newPrices();
      for (Price p : newPrices)
      {
        try
        {
          stockInfo = APIRequest(p.getSymbol(), RequestType.INTRADAY);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
        TradingData tradingData = newestStock(stockInfo);
        System.out.println("Checking company " + p.getSymbol());
        if (now.getTime() - p.getTimestamp().getTime() > 1
            && p.getTimestamp().getTime() != timestampOfCompany.getTime())
        {
          try
          {
            System.out.println(
                "Updating company " + p.getSymbol() + " with price "
                    + tradingData.getClose());
            p.setPrice(tradingData.getClose());
            Company company = new Company(p.getSymbol(), p.getSymbol());
            company.setCurrentPrice(p.getPrice());
            priceHistoryPersistence
                .save(p.getSymbol(), tradingData, timestampOfCompany);
            companiesPersistence.update(company);
            property.firePropertyChange("Price", p.getSymbol(),
                new Message(null, p));
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      }
      try
      {
        Thread.sleep(600000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   *
   * @param listener
   * @param propertyNames
   * @return
   */
  @Override public boolean addListener(
      GeneralListener<String, Message> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, Message> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }
}
