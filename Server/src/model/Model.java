package model;


import java.sql.SQLException;
import java.util.ArrayList;

public interface Model extends LocalSubject<String,Order> {

    boolean login(User user) throws Exception;

    boolean registerUser(User user) throws Exception;


    double getBalance(UserName userName);
    void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException;

    ArrayList<Company> getAllCompanies();

    Company getCompanyBySymbol(String symbol);

    Company getComapnyByName(String name);

    void AddOrder(Order order);

    Orders getPortfolioOrders(User user);

    User getUser(String name);

    ArrayList<Stock> LoaduserStocks(String name);

    Double getPriceTotal(String name);


    //tmp
//    void saveDataToFiles();
}
