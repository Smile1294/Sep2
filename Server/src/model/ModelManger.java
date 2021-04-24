package model;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManger implements Model {
    private Orders orders;
    private User user;
    private Stocks stocks;
    private UserList userList;
    private PropertyChangeSupport property;
    private Companies companies;

    public ModelManger() throws IOException {
//        this.property = new PropertyChangeSupport(this);
        this.companies = new Companies();
        companies.AddCompany(new Company("Tesla Inc.","TSLA"));
        userList = new UserList();
        orders = new Orders();
        user = new User("bob", "123");
        stocks = new Stocks("Market");
        stocks.addStock(new Stock("Apple", 5, 5));
        stocks.addStock(new Stock("Microsoft", 5, 5));
        stocks.addStock(new Stock("Kebab", 5, 5));
        user.setBalance(500);
        user.Buy(stocks.getStock(0),4);
        user.Buy(stocks.getStock(1),2);
        stocks.getStock(0).setPrice(242);
        System.out.println(user.getStocks());
    }

    public String getInfoAboutCompany(Company company)
    {
        return companies.getCompany(company).toString();
    }

    public Stocks getStocks() {
        return stocks;
    }

    @Override
    public ArrayList<Stock> getAllStocks() {
        return stocks.getAllStocks();
    }

    @Override
    public int getBalance(String usr){
        return user.getBalance();
//        return userList.getUser(usr).getBalance();
    }

    @Override
    public void fuckYourMoney(Double amount) {
        user.setBalance((int)(user.getBalance()-amount));
    }

    @Override
    public void smellyMess(Double amount) {
        user.setBalance((int)(user.getBalance()+amount));
    }


    public User getUser()
    {
        return user;
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
        if (!userList.userExist(usr,pwd)){
            throw new Exception("Wrong username or password");
        }
        return true;
    }

    @Override
    public boolean registerUser(String usr, String pwd) throws Exception {
        boolean result = userList.addProfile(usr, pwd);
//        property.firePropertyChange("Register",usr,"Success");
        return result;
    }

}
