package model;

public interface Model {
    public Stocks getStocks();
    public Orders getOrders();
    public void PlaceOrdertoSell(Stock stock,int amount,int price);
    public void PlaceOrdertoBuy(Stock stock,int amount,int price) throws Exception;
    boolean login(String usr,String pwd) throws Exception;
    boolean registerUser(String usr,String pwd) throws Exception;
}
