package persistence;

import model.Company;
import model.Stock;
import model.Stocks;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * StocksPersistence is interface for loading and saving Stocks
 */
public interface StocksPersistence {
    /**
     * Loads stocks for specific user and for specific company
     *
     * @param user user that owns the stocks
     * @param company company of the stocks
     * @return stock from database if not found will return null
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    Stock load(User user, Company company) throws SQLException;

    /**
     * updates all stocks in database with stocks list
     *
     * @param stock list will be updated to database
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void update(Stocks stock) throws SQLException;

    /**
     * Saves specific stock to database
     *
     * @param stock stock to be saved
     * @param user owner of the stock
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void save(Stock stock, User user) throws SQLException;

    /**
     * removes stock from database
     * @param stock stock to be removed
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void remove(Stock stock) throws SQLException;

    /**
     * loads all of stocks from database
     *
     * @return stocks list from database
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    Stocks loadAll() throws SQLException;

    /**
     * Saves all stocks to database
     *
     * @param stocks stocks to be saved
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void saveAll(Stocks stocks) throws SQLException;
}
