package persistence;


import stockAPI.TradingData;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class PriceHistoryDatabase implements PriceHistoryPersistence
{
  private static PriceHistoryDatabase instance;
  private ArrayList<TradingData> asd;

  private PriceHistoryDatabase() {

  }

  public synchronized static PriceHistoryDatabase getInstance() {
    if (instance == null) {
      instance = new PriceHistoryDatabase();
    }
    return instance;
  }


  @Override public void save(TradingData tradingData) throws SQLException
  {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    try (Connection connection = GetConnection.get()) {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO stockpricehistory(date_time, open, low, high, close, volume, symbol) VALUES (?,?,?,?,?,?,?)");
        statement.setTimestamp(1, timestamp);
        statement.setDouble(2, tradingData.getOpen());
        statement.setDouble(3, tradingData.getLow());
        statement.setDouble(4, tradingData.getHigh());
        statement.setDouble(5, tradingData.getClose());
        statement.setLong(6, tradingData.getVolume());
        statement.setString(7, "APPL");
      statement.executeUpdate();
    }
  }

  @Override public ArrayList<stockAPI.TradingData> load() throws SQLException
  {
    try (Connection connection = GetConnection.get()) {
      PreparedStatement statement = connection.prepareStatement("select * from stockpricehistory");
      ResultSet resultSet = statement.executeQuery();
      asd = new ArrayList<>();

      while (resultSet.next()) {
        ZonedDateTime date = null;
        double open = resultSet.getDouble("open");
        double low = resultSet.getDouble("low");
        double high = resultSet.getDouble("high");
        double close = resultSet.getDouble("close");
        long volume = resultSet.getLong("volume");
        TradingData tradingData = new TradingData(open,high,low,close,volume);
        asd.add(tradingData);
      }
      return asd;
    }
  }
}
