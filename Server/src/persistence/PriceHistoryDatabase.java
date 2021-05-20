package persistence;



import model.Price;
import stockAPI.TradingData;

import java.sql.*;
import java.util.ArrayList;

public class PriceHistoryDatabase implements PriceHistoryPersistence
{
  private static PriceHistoryDatabase instance;

  private PriceHistoryDatabase() {

  }

  public synchronized static PriceHistoryDatabase getInstance() {
    if (instance == null) {
      instance = new PriceHistoryDatabase();
    }
    return instance;
  }


  @Override public void save(String symbol, TradingData tradingData, Timestamp timestamp) throws SQLException
  {
    try (Connection connection = GetConnection.get()) {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO stockpricehistory(date_time, open, low, high, close, volume, symbol) VALUES (?,?,?,?,?,?,?)");
      statement.setTimestamp(1, timestamp);
      statement.setDouble(2, tradingData.getOpen());
      statement.setDouble(3, tradingData.getLow());
      statement.setDouble(4, tradingData.getHigh());
      statement.setDouble(5, tradingData.getClose());
      statement.setLong(6, tradingData.getVolume());
      statement.setString(7, symbol);
      statement.executeUpdate();
    }
  }


  @Override public ArrayList<Price> load() throws SQLException
  {
    try (Connection connection = GetConnection.get()) {
      PreparedStatement statement = connection.prepareStatement("select * from stockpricehistory");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Price> d = new ArrayList<>();

      while (resultSet.next()) {
        Timestamp timestamp = resultSet.getTimestamp("date_time");
        String name = resultSet.getString("symbol");
        double open = resultSet.getDouble("open");
        double low = resultSet.getDouble("low");
        double high = resultSet.getDouble("high");
        double close = resultSet.getDouble("close");
        long volume = resultSet.getLong("volume");
        Price price = new Price(timestamp,name,close);
        d.add(price);
      }

      return d;
    }

  }
}
