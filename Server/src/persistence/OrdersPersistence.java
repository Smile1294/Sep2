package persistence;

import model.Order;
import model.Orders;

import java.sql.SQLException;


/**
 * OrdersPersistence is interface for loading and saving orders
 */

public interface OrdersPersistence {

    /**
     * loading orders list from a database
     * @return Orders list of orders that is from database
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    Orders load() throws SQLException;

    /**
     * Updates all of orders in database
     * @param order updates with list of orders in databases
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void update(Orders order)throws SQLException;


    /**
     * saving orders list into a database
     * @param order order to be saved
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void save(Order order)throws SQLException;

    /**
     * removes order from database
     * @param  order order to be removed
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void remove(Order order)throws SQLException;





}
