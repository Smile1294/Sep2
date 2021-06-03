package persistence;



import model.Price;
import model.Prices;

import java.sql.SQLException;
/**
 * PriceHistoryDatabase is used to load/save from/to database prices
 */
public interface PriceHistoryPersistence
{
  /**
   * saves Price into database
   *
   * @param price Price to be saved
   * @throws SQLException if a database access error occurs or this method is called on a closed connection
   */
  void save(Price price)throws SQLException;

  /**
   * Loads Prices from database
   *
   * @return Prices
   * @throws SQLException if a database access error occurs or this method is called on a closed connection
   */
  Prices load() throws SQLException;
}
