package persistence;


import stockAPI.StockInfo;
import stockAPI.TradingData;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;


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


  @Override public void save(StockInfo stockInfo) throws SQLException
  {
    try (Connection connection = GetConnection.get()) {
      for(int x = 0; x < stockInfo.getTimeSeries().size(); x++)
      {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO stockpricehistory(date_time, open, low, high, close, volume, symbol) VALUES (?,?,?,?,?,?,?)");
        statement.setTimestamp(1, null);
        statement.setDouble(2, stockInfo.getTimeSeries().get(x).getOpen());
        statement.setDouble(3, stockInfo.getTimeSeries().get(x).getLow());
        statement.setDouble(4, stockInfo.getTimeSeries().get(x).getHigh());
        statement.setDouble(5, stockInfo.getTimeSeries().get(x).getClose());
        statement.setLong(6, stockInfo.getTimeSeries().get(x).getVolume());
        statement.setString(7, "APPL");
      }
    }
  }

  @Override public StockInfo load() throws SQLException
  {
    try (Connection connection = GetConnection.get()) {
      PreparedStatement statement = connection.prepareStatement("select * from stockpricehistory");
      ResultSet resultSet = statement.executeQuery();
      StockInfo stockInfo = new StockInfo();

      while (resultSet.next()) {
        ZonedDateTime date = null;
        double open = resultSet.getDouble("open");
        double low = resultSet.getDouble("low");
        double high = resultSet.getDouble("high");
        double close = resultSet.getDouble("close");
        long volume = resultSet.getLong("volume");
        stockInfo.getTimeSeries().add(new TradingData(open,high,low,close,volume));
      }
      return stockInfo;
    }
  }
}
