package persistence;

import model.Order;
import model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersDatabase implements OrdersPersistence{
    private static OrdersDatabase instance;


    private OrdersDatabase() { }

    public synchronized static OrdersDatabase getInstance() {
        if (instance == null){
            instance = new OrdersDatabase();
        }
        return instance;
    }

    @Override
    public Orders load() throws SQLException {
        try (Connection connection = GetConnection.get()){
            PreparedStatement statement = connection.prepareStatement("select * from order");
            return null;
        }
    }

    @Override
    public void update(Order order) throws SQLException {

    }

    @Override
    public void save(Order order) throws SQLException {

    }

    @Override
    public void remove(Order order) throws SQLException {

    }
}
