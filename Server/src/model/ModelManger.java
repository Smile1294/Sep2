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

/**
 * ModelManager implements model interface and implements functionality
 */

public class ModelManger implements Model {
    private Orders orders;
    private Companies companies;
    private UserList userList;
    private UserListPersistence userListPersistence;
    private CompaniesPersistence companiesPersistence;
    private OrdersPersistence ordersPersistence;
    private Stocks stocks;
    private Thread thread;

    /**
     * Constructor initialing all the instance variables
     * @throws IOException
     */

    public ModelManger() throws IOException {
        userListPersistence = new UserListFile();
        companiesPersistence = new CompaniesFiles();
        ordersPersistence = new OrdersFile();
        userList = userListPersistence.load("users.json");
        orders = ordersPersistence.load("orders.json");
        companies = companiesPersistence.load("companies.json");
        this.stocks = new Stocks();
        thread = new Thread(orders);
        thread.start();



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


    /**
     * gets the user by name
     * @param name name of the user
     * @return user
     */

    public User getUser(String name) {
        return userList.getUser(new UserName(name));
    }

    /**
     * gets and loads users stocks
     * @param name name of the user
     * @return stock/s
     */

    public ArrayList<Stock> LoaduserStocks(String name) {
        ArrayList<Stock> temporaryList = new ArrayList<Stock>();
        try {
            for (Company s : getAllCompanies()) {
                getUser(name).getStocks().getStockBySymbol(s.getSymbol()).setAmount(orders.getCompeltedUserOwnedStock(s.getSymbol(), name));
                temporaryList.add(getUser(name).getStocks().getStockBySymbol(s.getSymbol()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return temporaryList;
    }

    public Orders getPortfolioOrders(User user) {
        return orders.getOrderByUser(user);
    }


    /**
     * gets users total stocks amount
     * @param name name of the user
     * @return stock amount
     */

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

    /**
     * adds an order
     * @param order order that is getting added
     */

    public void AddOrder(Order order) {

        if (order.isSell()) {
            if (getUser(order.getUser()).getStocks().getStockBySymbol(order.getSymbol()).getAmount() > order.getAmount()) {
                orders.AddOrder(order);
                System.out.println("Added order for sale");
            } else {
                System.out.println("Insufficient resources");
            }
        } else {
            if (getUser(order.getUser()).getBalance() > order.getAskingPrice()) {
                orders.AddOrder(order);
                System.out.println("Added order to buy");
            } else {
                System.out.println("Not enough money to place order to buy");
            }
        }

    /**
     * buying stock
     * @param stock stock that user want to buy
     * @param user user that wants to buy
     * @param Amount amount of stock
     */

    public void buyStock(Stock stock, User user, int Amount) {
        user.BuyStock(new Stock(stock.getCompany(), Amount));
    }

    /**
     * gets the balance of the user
     * @param userName username of the user
     * @return balance
     */

    @Override
    public double getBalance(UserName userName) {
        return userList.getBalance(userName);
    }

    /**
     * Withdrawing or depositing money
     * @param userName Username of the user that is transferring money
     * @param amount amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     */

    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) {
        userList.transferMoney(userName, amount, isWithdraw);
    }

    /**
     * gets all the companies
     * @return companies
     */

    @Override
    public ArrayList<Company> getAllCompanies() {
        return companies.getCompanies();
    }

    /**
     * gets the company by symbol
     * @param symbol symbol that is being compared to
     * @return company
     */

    @Override
    public Company getCompanyBySymbol(String symbol) {
        return companies.getCompanyBySymbol(symbol);
    }


    public Company getComapnyByName(String name) {
        return companies.getCompanyByName(name);
    /**
     * getting the stock from a user
     * @param user user that we are getting stock from
     * @return stock
     */

    public Stocks getUserStocks(User user) {
        return user.getStocks();
    }


    /**
     *  saving data to the files
     */

    @Override
    public void saveDataToFiles() {
        userListPersistence.save(userList, "users.json");
        ordersPersistence.save(orders, "orders.json");
        companiesPersistence.save(companies, "companies.json");
        thread.stop();
    }

    /**
     * login for user
     * @param user user that wants login
     * @return
     * @throws Exception
     */

    @Override
    public boolean login(User user) throws Exception {
        if (!userList.userExist(user)) {
            throw new Exception("Wrong username or password");
        }
        return true;
    }

    /**
     * adding registered user to the list
     * @param user user that is being added
     * @return
     * @throws Exception
     */

    @Override
    public boolean registerUser(User user) throws Exception {
        boolean result = userList.addUser(user);
        return result;
    }


}
