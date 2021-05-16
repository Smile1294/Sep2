package persistence;


import model.Company;
import stockAPI.TradingData;

import java.sql.*;
import java.util.ArrayList;

public class PriceHistoryDatabase implements PriceHistoryPersistence
{
  private static PriceHistoryDatabase instance;
  private ArrayList<Company> asd;

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
      statement.setString(7, "FB");
      statement.executeUpdate();
    }
  }

  @Override public Company loadNewestOf(String symbol) throws SQLException
  {
    try (Connection connection = GetConnection.get()) {
      PreparedStatement statement = connection.prepareStatement("select * from stockpricehistory WHERE symbol = ?");
      statement.setString(1,symbol);
      ResultSet resultSet = statement.executeQuery();
      String name = null;
      double close = 0;

      while (resultSet.next()) {
        name = resultSet.getString("symbol");
        close = resultSet.getDouble("close");
      }
      Company company = new Company(name, name);
      company.setCurrentPrice(close);
      return company;
    }
  }

  @Override public ArrayList<Company> loadAllOf(String symbol)
      throws SQLException
  {
    try (Connection connection = GetConnection.get()) {
      PreparedStatement statement = connection.prepareStatement("select * from stockpricehistory WHERE symbol = ?");
      statement.setString(1,symbol);
      ResultSet resultSet = statement.executeQuery();
      asd = new ArrayList<>();

      while (resultSet.next()) {
        String name = resultSet.getString("symbol");
        double close = resultSet.getDouble("close");
        Company company = new Company(name, name);
        company.setCurrentPrice(close);
        asd.add(company);
      }
      return asd;
    }
  }
}
