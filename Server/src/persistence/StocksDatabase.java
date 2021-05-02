package persistence;

import model.Company;
import model.Stock;
import model.Stocks;
import model.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StocksDatabase implements StocksPersistence{
    private static StocksDatabase instance;

    private StocksDatabase() { }

    public synchronized static StocksDatabase getInstance() {
        if (instance == null){
            instance = new StocksDatabase();
        }
        return instance;
    }

    @Override
    public Stock load(User user, Company company) throws SQLException {
        try (Connection connection = GetConnection.get()) {
            PreparedStatement statement = connection.prepareStatement("select * from Stock where user_name = ? and symbol = ?");
            statement.setString(1, user.getUserName().toString());
            statement.setString(2, company.getSymbol());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                int amount = resultSet.getInt("amount");
                Stock stock = new Stock(company,amount);
                return stock;
            }

            return null;
        }
    }

    @Override
    public void update(Stock stock) throws SQLException {

    }

    @Override
    public void save(Stock stock) throws SQLException {

    }

    @Override
    public void remove(Stock stock) throws SQLException {

    }
}
