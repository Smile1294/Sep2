package model;

import mediator.Symbol;
import persistence.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelManger implements Model {
    private Orders orders;
    private Companies companies;
    private UserList userList;
    private UsersPersistence usersPersistence;
    private CompaniesPersistence companiesPersistence;
    private OrdersPersistence ordersPersistence;
    private StocksPersistence stocksPersistence;

    public ModelManger() throws IOException, SQLException {
        usersPersistence = UsersDatabase.getInstance();
        companiesPersistence = CompaniesDatabase.getInstance();
        ordersPersistence = OrdersDatabase.getInstance();
        stocksPersistence = StocksDatabase.getInstance();

        userList = usersPersistence.load();
        companies = companiesPersistence.load();
        orders = ordersPersistence.load();

        for (User u : userList.getUsers()){
            for (Company c : companies.getCompanies()){
                u.addStock(stocksPersistence.load(u, c));
            }
        }
    }

    public User getUser(String name) {
        return userList.getUser(new UserName(name));
    }

    public ArrayList<Stock> loadUserStocks(String name) {
        ArrayList<Stock> temporaryList = new ArrayList<Stock>();
        for (Stock s : getUser(getUser(name).getUserName().getName()).getStocks().getAllStocks()) {
            temporaryList.add(s);
        }
        return temporaryList;
    }

    public Double getPriceTotal(String name) {
        double d = 0.0;
        try {
            for (Stock s : loadUserStocks(name)) {
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
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException {
        userList.transferMoney(userName, amount, isWithdraw);
        usersPersistence.update(userList.getUser(userName));
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
