package persistence;

import model.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StocksDatabase implements StocksPersistence {
    private static StocksDatabase instance;

    private StocksDatabase() {
    }

    public synchronized static StocksDatabase getInstance() {
        if (instance == null) {
            instance = new StocksDatabase();
        }
        return instance;
    }

    @Override
    public Stock load(User user, Company company) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("select * from stock where user_name = ?  and symbol = ?");
            statement.setString(1, user.getUserName().toString());
            statement.setString(2, company.getSymbol());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("user_name");
                String symbol = resultSet.getString("symbol");
                int amount = resultSet.getInt("amount");
                return new Stock(symbol, username, amount);
            }
            return null;
        }
    }

    @Override
    public Stocks loadAll() throws SQLException {
        Stocks stocks = new Stocks();
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("select * from stock");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("user_name");
                String symbol = resultSet.getString("symbol");
                int amount = resultSet.getInt("amount");
                stocks.addStock(new Stock(symbol, username, amount));
            }
            return stocks;
        }
    }

    @Override
    public void update(Stocks stocks) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            for (Stock s : stocks.getAllStocks()) {
                try {
                    PreparedStatement statement = connection.prepareStatement("UPDATE stock SET amount = ? where user_name = ? AND symbol = ?");
                    statement.setString(2, s.getUsername());
                    statement.setString(3, s.getSymbol());
                    statement.setInt(1, s.getAmount());
                    statement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public void save(Stock stock, User user) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("insert into stock(user_name,symbol,amount,price)values (?,?,?,?)");
            statement.setString(1, user.getUserName().getName());
            statement.setString(2, stock.getSymbol());
            statement.setInt(3, stock.getAmount());
            statement.setInt(4, 0);
            statement.executeUpdate();
        }
    }

    @Override
    public void saveAll(Stocks stocks) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            for (Stock s : stocks.getAllStocks()) {
                try {
                    PreparedStatement statement = connection.prepareStatement("insert into stock(user_name,symbol,amount)values (?,?,?)");
                    statement.setString(1, s.getUsername());
                    statement.setString(2, s.getSymbol());
                    statement.setInt(3, s.getAmount());
                    statement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public void remove(Stock stock) throws SQLException {

    }
}
