package persistence;



import model.Company;
import model.Price;
import stockAPI.TradingData;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface PriceHistoryPersistence
{
  void save(String symbol, TradingData tradingData, Timestamp timestamp)throws SQLException;

  ArrayList<Price> load() throws SQLException;
}
