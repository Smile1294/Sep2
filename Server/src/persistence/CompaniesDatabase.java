package persistence;

import model.*;

import java.sql.*;

public class CompaniesDatabase implements CompaniesPersistence{
    private static CompaniesDatabase instance;
    private static final String URL = "jdbc:postgresql://tai.db.elephantsql.com:5432/swjaurgb?currentSchema=sep2";
    private static final String USER = "swjaurgb";
    private static final String PASSWORD = "3CqmA8u3ha9nknGYO1D7FqdQ072gixMo";

    private CompaniesDatabase() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized CompaniesDatabase getInstance() throws SQLException{
        if (instance == null){
            instance = new CompaniesDatabase();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public Companies load() throws SQLException {
        try (Connection connection = getConnection()){
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
                companies.addCompany(company);
            }
            return companies;
        }
    }

    @Override
    public void update(Company company) throws SQLException {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("update Company set price = ? where symbol = ?");
            statement.setInt(1, (int)company.getCurrentPrice());
            statement.setString(2, company.getSymbol());
            statement.executeUpdate();
        }
    }

    @Override
    public void save(Company company) throws SQLException {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("insert into Company(symbol,name,price)values (?,?,?)");
            statement.setString(1, company.getSymbol());
            statement.setString(2, company.getName());
//            statement.setString(3,user.getEmail().toString());
            statement.setInt(3, (int)company.getCurrentPrice());
            statement.executeUpdate();
        }
    }

    @Override
    public void remove(Company company) throws SQLException {
        try (Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("delete from Company where symbol = ?");
            statement.setString(1,company.getSymbol());
            statement.executeUpdate();
        }
    }
}
