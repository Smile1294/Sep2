package persistence;



import stockAPI.StockInfo;
import stockAPI.TradingData;

import java.sql.SQLException;

public interface PriceHistoryPersistence
{
  void save(TradingData tradingData)throws SQLException;

  StockInfo load() throws SQLException;
}
