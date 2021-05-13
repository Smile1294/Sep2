package persistence;

import model.Order;
import model.Orders;

import java.sql.SQLException;

public interface OrdersPersistence {
    Orders load() throws SQLException;
    void update(Orders order)throws SQLException;
    void save(Order order)throws SQLException;
    void remove(Order order)throws SQLException;
}
