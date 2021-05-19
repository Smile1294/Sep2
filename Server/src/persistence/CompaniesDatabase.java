package persistence;

import model.*;
import java.sql.*;

/**
 * CompaniesDatabase is used for saving/loading data to/from database about companies
 */
public class CompaniesDatabase implements CompaniesPersistence{
    private static CompaniesDatabase instance;

    private CompaniesDatabase() { }

    public static synchronized CompaniesDatabase getInstance() {
        if (instance == null){
            instance = new CompaniesDatabase();
        }
        return instance;
    }

    /**
     * Loads all comapnies from database
     * @return comapnies list from database
     * @throws SQLException
     */
    @Override
    public Companies load() throws SQLException {
        try (Connection connection = GetConnection.get()){
            PreparedStatement statement = connection.prepareStatement("select * from Company");
            ResultSet resultSet = statement.executeQuery();
            Companies companies = new Companies();

            while (resultSet.next()){
                String symbol = resultSet.getString("symbol");
                String name = resultSet.getString("name");
                String definition = resultSet.getString("definition");
                int price = resultSet.getInt("price");
                Company company = new Company(name, symbol);
                company.setCurrentPrice(price);
                companies.AddCompany(company);
            }
            return companies;
        }
    }

    /**
     * Updates comapny in database
     * @param company
     * @throws SQLException
     */
    @Override
    public void update(Company company) throws SQLException {
        try (Connection connection = GetConnection.get()){
            PreparedStatement statement = connection.prepareStatement("update Company set price = ? where symbol = ?");
            statement.setInt(1, (int)Math.round(company.getCurrentPrice()));
            statement.setString(2, company.getSymbol());
            statement.executeUpdate();
        }
    }

    /**
     * Saves company to database
     * @param company is company that is beeing saved
     * @throws SQLException
     */
    @Override
    public void save(Company company) throws SQLException {
        try (Connection connection = GetConnection.get()){
            PreparedStatement statement = connection.prepareStatement("insert into Company(symbol,name,price)values (?,?,?)");
            statement.setString(1, company.getSymbol());
            statement.setString(2, company.getName());
//          statement.setString(3,user.getEmail().toString());
            statement.setInt(3, (int)Math.round(company.getCurrentPrice()));
            statement.executeUpdate();
        }
    }

    /**
     * Removes from database
     * @param company will be removed from database
     * @throws SQLException
     */
    @Override
    public void remove(Company company) throws SQLException {
        try (Connection connection = GetConnection.get()){
            PreparedStatement statement = connection.prepareStatement("delete from Company where symbol = ?");
            statement.setString(1,company.getSymbol());
            statement.executeUpdate();
        }
    }
}
