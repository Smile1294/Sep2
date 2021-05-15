package persistence;



import stockAPI.StockInfo;
import stockAPI.TradingData;

import java.sql.SQLException;

public interface PriceHistoryPersistence
{
  void save(StockInfo stockInfo)throws SQLException;

  StockInfo load() throws SQLException;
}
