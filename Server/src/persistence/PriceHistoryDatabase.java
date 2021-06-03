package persistence;



import model.Price;
import model.Prices;

import java.sql.*;

/**
 * PriceHistoryDatabase is used to load/save from/to database prices
 */
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

  /**
   * saves Price into database
   *
   * @param price Price to be saved
   * @throws SQLException if a database access error occurs or this method is called on a closed connection
   */
  @Override public void save(Price price) throws SQLException
  {
    try (Connection connection = GetConnection.get()) {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO stockpricehistory(date_time, open, low, high, close, volume, symbol) VALUES (?,?,?,?,?,?,?)");
      statement.setTimestamp(1, price.getTimestamp());
      statement.setDouble(2, price.getOpen());
      statement.setDouble(3, price.getLow());
      statement.setDouble(4, price.getHigh());
      statement.setDouble(5, price.getPrice());
      statement.setLong(6, price.getVolume());
      statement.setString(7, price.getSymbol());
      statement.executeUpdate();
    }
  }

  /**
   * Loads Prices from database
   *
   * @return Prices
   * @throws SQLException if a database access error occurs or this method is called on a closed connection
   */
  @Override public Prices load() throws SQLException
  {
    try (Connection connection = GetConnection.get()) {
      PreparedStatement statement = connection.prepareStatement("select * from stockpricehistory");
      ResultSet resultSet = statement.executeQuery();
      Prices d = new Prices();

      while (resultSet.next()) {
        Timestamp timestamp = resultSet.getTimestamp("date_time");
        String name = resultSet.getString("symbol");
        double open = resultSet.getDouble("open");
        double low = resultSet.getDouble("low");
        double high = resultSet.getDouble("high");
        double close = resultSet.getDouble("close");
        Long volume = resultSet.getLong("volume");
        Price price = new Price(timestamp,name,open,low,high,close,volume);
        d.addPrice(price);
      }

      return d;
    }

  }
}
