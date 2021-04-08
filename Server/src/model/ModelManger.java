package model;

import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ModelManger implements Model {
    private Orders orders;
    private User user;
    private Stocks stocks;
    private UserList userList;
   private PropertyChangeSupport property;

    public ModelManger() throws IOException {
//        this.property = new PropertyChangeSupport(this);
        userList = new UserList();
        orders = new Orders();
        user = new User("bob", "123");
        stocks = new Stocks("Market");
        stocks.addStock(new Stock("Apple", 5, 5));
        stocks.addStock(new Stock("Microsoft", 5, 5));
        stocks.addStock(new Stock("Kebab", 5, 5));
    }

    public Stocks getStocks() {
        return stocks;
    }

    public Orders getOrders() {
        return orders;
    }

    public void PlaceOrdertoSell(Stock stock, int amount, int price) {
        user.addOrdertoSell(stock, amount, price);

    }

    public void PlaceOrdertoBuy(Stock stock, int amount, int price) throws Exception {
        user.addOrderToBuy(stock, amount, price);
    }

    @Override
    public boolean login(String usr, String pwd) throws Exception {
        boolean result = userList.login(usr, pwd);
//        property.firePropertyChange("Login",usr,"Success");
        return result;
    }

    @Override
    public boolean registerUser(String usr, String pwd) throws Exception {
        boolean result = userList.addProfile(usr, pwd);
//        property.firePropertyChange("Register",usr,"Success");
        return result;
    }

}
