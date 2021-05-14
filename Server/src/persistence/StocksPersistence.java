package persistence;

import model.Company;
import model.Stock;
import model.Stocks;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StocksPersistence {
    Stock load(User user, Company company) throws SQLException;
    void update(Stock stock)throws SQLException;
    void save(Stock stock,User user)throws SQLException;
    void remove(Stock stock)throws SQLException;
    Stocks loadAll() throws SQLException;
}
