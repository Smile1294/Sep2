package persistence;

import model.Orders;
import model.UserList;


/**
 * OrdersPersistence is interface for loading and saving orders
 */

public interface OrdersPersistence {

    /**
     * saving orders list into a file
     * @param ordersList list of orders that is being stored
     * @param filename name of a file that is information stored on
     */


    void save(Orders ordersList, String filename);

    /**
     * loading orders list from a file
     * @param fileName from file to load
     * @return order list
     */

    Orders load(String fileName);
}
