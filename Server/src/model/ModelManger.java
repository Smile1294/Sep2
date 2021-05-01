package model;

import filePersistence.*;
import persistence.UsersDatabase;
import persistence.UsersPersistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManger implements Model {
    private Orders orders;
    private Companies companies;
    private UserList userList;
    private UsersPersistence usersPersistence;
//    private UserListPersistence userListPersistence;
//    private CompaniesPersistence companiesPersistence;
//    private OrdersPersistence ordersPersistence;


    public ModelManger() throws IOException, SQLException {
        usersPersistence = UsersDatabase.getInstance();

        userList = usersPersistence.load();

//        userListPersistence = new UserListFile();
//        companiesPersistence = new CompaniesFiles();
//        ordersPersistence = new OrdersFile();
//        userList = userListPersistence.load("users.json");
//        orders = ordersPersistence.load("orders.json");
//        companies = companiesPersistence.load("companies.json");



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

//        System.out.println(orders);
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
    public Double getPriceTotal(String name) {
        double d = 0.0;
        try {
            for (Stock s : LoaduserStocks(name)) {
                d = d + s.getPrice() * getUser(name).getStocks().getStock(s).getAmount();
            }
            return  d;
        } catch (Exception e) {
            System.out.println(e);
       }
        return d;
    }


    public void AddOrder(Order order) {
        orders.AddOrder(order);
    }

    public void buyStock(Stock stock, User user, int Amount) {
        user.BuyStock(new Stock(stock.getCompany(), Amount));
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
    public Company getCompany(String symbol) {
        return companies.getCompany(symbol);
    }

    public Stocks getUserStocks(User user) {
        return user.getStocks();
    }

//    @Override
//    public void saveDataToFiles() {
//        userListPersistence.save(userList, "users.json");
//        ordersPersistence.save(orders, "orders.json");
//        companiesPersistence.save(companies, "companies.json");
//    }

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
        if (result){
            usersPersistence.save(user);
        }
        return result;
    }
}
