package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import mediator.Symbol;
import persistence.*;
import viewmodel.SimpleStockViewModel;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ModelManger implements Model {
    private Orders orders;
    private Companies companies;
    private UserList userList;
    private UserListPersistence userListPersistence;
    private CompaniesPersistence companiesPersistence;
    private OrdersPersistence ordersPersistence;
    private Stocks stocks;


    public ModelManger() throws IOException {
        userListPersistence = new UserListFile();
        companiesPersistence = new CompaniesFiles();
        ordersPersistence = new OrdersFile();
        userList = userListPersistence.load("users.json");
        orders = ordersPersistence.load("orders.json");
        companies = companiesPersistence.load("companies.json");
        Thread t = new Thread(orders);
        t.start();
        this.stocks = new Stocks();
        for (int i = 0; i < companies.getCompanies().size(); i++) {
            stocks.addStock(new Stock(companies.getCompanies().get(i).getSymbol(), "Admin", 200));
        }
        System.out.println(stocks.toString());

//        companies.AddCompany(new Company("Apple Inc.", Symbol.APPLE.getSymbol()));
//        companies.AddCompany(new Company("Alphabet Inc. Class A.", Symbol.GOOGLEA.getSymbol()));
//        companies.AddCompany(new Company("Tesla Inc.", Symbol.TESLA.getSymbol()));
//        companies.AddCompany(new Company("Facebook Inc.", Symbol.FACEBOOK.getSymbol()));
//        companies.AddCompany(new Company("Paypal Holdings Inc.", Symbol.PAYPAL.getSymbol()));
//        companies.AddCompany(new Company("Microsoft Corporation", Symbol.MICROSOFT.getSymbol()));
//        companies.AddCompany(new Company("Amazon.com Inc.", Symbol.AMAZON.getSymbol()));
//        companies.AddCompany(new Company("Alphabet Inc. Class C", Symbol.GOOGLEC.getSymbol()));
//        companies.AddCompany(new Company("International Business Machines Corporation", Symbol.IBM.getSymbol()));

//        for (Company c : companies.getCompanies()){
//            c.setCurrentPrice(Math.random()*1000);

//        }

        System.out.println(orders);
    }



    public User getUser(String name) {
        return userList.getUser(new UserName(name));
    }

    public ArrayList<Stock> LoaduserStocks(String name) {
        ArrayList<Stock> temporaryList = new ArrayList<Stock>();
        for (Stock s : getUser(getUser(name).getUserName().getName()).getStocks().getAllStocks()) {
            temporaryList.add(s);
        }
        return temporaryList;
    }
    public Orders getOrders(User user)
    {
        return orders.getOrderByUser(user);
    }

    public Double getPriceTotal(String name) {
        double d = 0.0;
        try {
            for (Company s : companies.getCompanies()) {
                d = d + s.getCurrentPrice() * getUser(name).getStocks().getStockBySymbol(s.getSymbol()).getAmount();
            }
            return d;
        } catch (Exception e) {
            System.out.println(e);
        }
        return d;
    }



    public void AddOrder(Order order) {

        orders.AddOrder(order);
    }

    public void buyStock(Stock stock, User user, int Amount) {
        user.BuyStock(new Stock(stock.getSymbol(), user.getUserName().getName(), Amount));
    }


    @Override
    public double getBalance(UserName userName) {
        return userList.getBalance(userName);
    }

    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) {
        userList.transferMoney(userName, amount, isWithdraw);
    }

    @Override
    public ArrayList<Company> getAllCompanies() {
        return companies.getCompanies();
    }

    @Override
    public Company getCompanyBySymbol(String symbol) {
        return companies.getCompanyBySymbol(symbol);
    }


    public Company getComapnyByName(String name) {
        return companies.getCompanyByName(name);
    }


    @Override
    public void saveDataToFiles() {
        userListPersistence.save(userList, "users.json");
        ordersPersistence.save(orders, "orders.json");
        companiesPersistence.save(companies, "companies.json");
    }

    @Override
    public boolean login(User user) throws Exception {
        if (!userList.userExist(user)) {
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
