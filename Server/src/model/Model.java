package model;


import java.util.ArrayList;

public interface Model {

    boolean login(User user) throws Exception;
    boolean registerUser(User user) throws Exception;


    double getBalance(UserName userName);
    void transferMoney(UserName userName, double amount, boolean isWithdraw);

    ArrayList<Company> getAllCompanies();

    Company getCompany(String symbol);

    //tmp
    void saveDataToFiles();
}
