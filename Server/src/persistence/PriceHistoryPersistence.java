package persistence;



import model.Company;
import stockAPI.TradingData;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PriceHistoryPersistence
{
  void save(TradingData tradingData)throws SQLException;

  Company loadNewestOf(String symbol) throws SQLException;

  ArrayList<Company> loadAllOf(String symbol) throws SQLException;
}
