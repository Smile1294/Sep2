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
     * @return order list
     */
    Orders load() throws SQLException;

    /**
     * updates orders in database
     */
    void update(Orders order)throws SQLException;


    /**
     * saving orders list into a database
     * @param order is saved to database
     * @throws SQLException
     */
    void save(Order order)throws SQLException;

    /**
     * removes order from database
     * @param  order is removed from database
     */
    void remove(Order order)throws SQLException;





}
