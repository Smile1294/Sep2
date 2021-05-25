package persistence;



import model.Price;
import model.Prices;

import java.sql.SQLException;

public interface PriceHistoryPersistence
{
  void save(Price price)throws SQLException;

  Prices load() throws SQLException;
}
