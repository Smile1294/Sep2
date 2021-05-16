package persistence;



import stockAPI.StockInfo;
import stockAPI.TradingData;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PriceHistoryPersistence
{
  void save(TradingData tradingData)throws SQLException;

  ArrayList<stockAPI.TradingData> load() throws SQLException;
}
