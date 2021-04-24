package model;

import java.util.ArrayList;

public interface Model {
    public Stocks getStocks();
    public ArrayList<Stock> getAllStocks();
    public Orders getOrders();
    User getUser();
    public void PlaceOrdertoSell(Stock stock,int amount,int price);
    public void PlaceOrdertoBuy(Stock stock,int amount,int price) throws Exception;
    boolean login(String usr,String pwd) throws Exception;
    boolean registerUser(String usr,String pwd) throws Exception;

    int getBalance(String usr);
    void fuckYourMoney(Double amount);
    void smellyMess(Double amount);
}
