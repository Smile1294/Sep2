package model;


import java.sql.SQLException;
import java.util.ArrayList;

public interface Model {

    boolean login(User user) throws Exception;
    boolean registerUser(User user) throws Exception;


    double getBalance(UserName userName);
    void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException;

    ArrayList<Company> getAllCompanies();

    Company getCompany(String symbol);

    User getUser(String name);
    ArrayList<Stock> loadUserStocks(String name);
    Double getPriceTotal(String name);




    //tmp
//    void saveDataToFiles();
}
