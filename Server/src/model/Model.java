package model;

import java.util.ArrayList;

public interface Model {
//    public Stocks getStocks();
//    public ArrayList<Stock> getAllStocks();
//    public Orders getOrders();
//    User getUser();
//    public void PlaceOrdertoSell(Stock stock,int amount,int price);
//    public void PlaceOrdertoBuy(Stock stock,int amount,int price) throws Exception;
    boolean login(User user) throws Exception;
    boolean registerUser(User user) throws Exception;


    double getBalance(UserName userName);
    void transferMoney(UserName userName, double amount, boolean isWithdraw);


    //tmp
    void saveUsersToFile();
}
