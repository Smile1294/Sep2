package persistence;

import model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 *OrdersDatabase is used to load/save from/to database orders
 */

public class OrdersDatabase implements OrdersPersistence {
    private static OrdersDatabase instance;


    private OrdersDatabase() {
    }

    public synchronized static OrdersDatabase getInstance() {
        if (instance == null) {
            instance = new OrdersDatabase();
        }
        return instance;
    }

    /**
     *Loads orders from database
     * @return Orders list of orders that is from database
     * @throws SQLException
     */
    @Override
    public Orders load() throws SQLException {
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("select * from Orders");
            ResultSet resultSet = statement.executeQuery();
            Orders Orders = new Orders();
            while (resultSet.next()) {
                boolean sell = resultSet.getBoolean("sale");
                BigDecimal askingPrice = resultSet.getBigDecimal("askingPrice");
                int initialAmount = resultSet.getInt("initialamount");
                Status status = Status.valueOf(resultSet.getString("status").toUpperCase());
                String username = resultSet.getString("username");
                String symbol = resultSet.getString("symbol");
                Order order = new Order(sell, askingPrice, initialAmount, username, status, symbol);
                order.setAmount(resultSet.getInt("amount"));
                order.setOrderId(UUID.fromString(resultSet.getString("order_id")));
                Orders.AddOrder(order);
            }
            return Orders;
        }

    }

    /**
     * Updates all of orders in database
     * @param orders updates with list of orders in databases
     * @throws SQLException
     */
    @Override
    public void update(Orders orders) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Orders SET amount = ? , status = ? WHERE order_id = ?;");
            for (Order o : orders.getOrders()) {
                statement.setInt(1, o.getAmount());
                statement.setString(2, o.getStatus().getStatus());
                statement.setObject(3, UUID.fromString(o.getOrderId()));
                statement.executeUpdate();
            }
        }
    }

    /**
     * Saves order to database
     * @param order is saved to database
     * @throws SQLException
     */
    @Override
    public void save(Order order) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("insert into Orders(sale,askingPrice,initialAmount,amount,status,username,symbol,order_id)values (?,?,?,?,?,?,?,?)");
            statement.setBoolean(1, order.isSell());
            statement.setDouble(2, order.getAskingPrice());
            statement.setInt(3, order.getInitialAmount());
            statement.setInt(4, order.getAmount());
            statement.setString(5, order.getStatus().getStatus());
            statement.setString(6, order.getUser());
            statement.setString(7, order.getSymbol());
            statement.setObject(8, UUID.fromString(order.getOrderId()));
            statement.execute();
        }
    }

    /**
     *
     * @param  order is removed from database
     * @throws SQLException
     */
    @Override
    public void remove(Order order) throws SQLException {

    }
}
