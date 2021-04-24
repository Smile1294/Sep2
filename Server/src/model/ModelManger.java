package model;

import persistence.UserListFile;
import persistence.UserListPersistence;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManger implements Model {
    private Orders orders;
    private Stocks stocks;
    private UserList userList;
    private UserListPersistence ulp;

    private Companies companies;

    public ModelManger() throws IOException {
        ulp = new UserListFile();
        this.companies = new Companies();
        companies.AddCompany(new Company("Tesla Inc.","TSLA"));
        userList = ulp.load("users.json");
        orders = new Orders();

        stocks = new Stocks("Market");
        stocks.addStock(new Stock("Apple", 5, 5));
        stocks.addStock(new Stock("Microsoft", 5, 5));
        stocks.addStock(new Stock("Kebab", 5, 5));

        stocks.getStock(0).setPrice(242);

    }

//    public String getInfoAboutCompany(Company company)
//    {
//        return companies.getCompany(company).toString();
//    }

//    public Stocks getStocks() {
//        return stocks;
//    }

//    @Override
//    public ArrayList<Stock> getAllStocks() {
//        return stocks.getAllStocks();
//    }

    @Override
    public double getBalance(UserName userName){
        return userList.getBalance(userName);
    }

    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw){
        userList.transferMoney(userName,amount,isWithdraw);
    }

    @Override
    public void saveUsersToFile() {
        ulp.save(userList, "users.json");
    }


//    public User getUser()
//    {
//        return user;
//    }
//
//    public Orders getOrders() {
//        return orders;
//    }

//    public void PlaceOrdertoSell(Stock stock, int amount, int price) {
//        user.addOrdertoSell(stock, amount, price);
//
//    }
//
//    public void PlaceOrdertoBuy(Stock stock, int amount, int price) throws Exception {
//        user.addOrderToBuy(stock, amount, price);
//    }



    @Override
    public boolean login(User user) throws Exception {
        if (!userList.userExist(user)){
            throw new Exception("Wrong username or password");
        }
        return true;
    }

    @Override
    public boolean registerUser(User user) throws Exception {
        boolean result = userList.addUser(user);
        return result;
    }

}
