package persistence;

import model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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
                int Amount = resultSet.getInt("amount");
                Status status = Status.valueOf(resultSet.getString("status").toUpperCase());
                String username = resultSet.getString("username");
                String symbol = resultSet.getString("symbol");
                Order order = new Order(sell, askingPrice, initialAmount, username, status, symbol);
                order.setAmount(resultSet.getInt("amount"));
                Orders.AddOrder(order);
            }
            return Orders;
        }

    }

    @Override
    public void update(Orders orders) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE Orders SET status = 'Completed' WHERE order_id = ?");
            // update more things ??? maybe not just set status to complete, what about amount or when canceling order????
            for (Order o : orders.getOrders()) {
                System.out.println(o);
                if (o.getStatus().equals(Status.COMPLETED)) {
                    System.out.println("updating " + o);
                    statement.setObject(1, UUID.fromString(o.getOrderId()));
                    statement.execute();
                }
            }
        }


    }

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
            statement.executeUpdate();
        }

    }

    @Override
    public void remove(Order order) throws SQLException {

    }
}
