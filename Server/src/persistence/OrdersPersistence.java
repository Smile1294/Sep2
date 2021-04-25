package persistence;

import model.Orders;
import model.UserList;

public interface OrdersPersistence {
    void save(Orders ordersList, String filename);
    Orders load(String fileName);
}
