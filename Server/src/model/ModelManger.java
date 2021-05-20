package model;

import mediator.TradingServer;
import persistence.*;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ModelManager implements model interface and implements functionality
 */

public class ModelManger implements Model {
    private Orders orders;
    private Companies companies;
    private UserList userList;
    private Stocks stocks;
    private Prices prices;
    private TradingServer tradingServer;
    private UsersPersistence usersPersistence;
    private CompaniesPersistence companiesPersistence;
    private OrdersPersistence ordersPersistence;
    private StocksPersistence stocksPersistence;


    /**
     * Constructor initialing all the instance variables
     * @throws IOException
     */

    public ModelManger() throws IOException, SQLException {

        prices = new Prices();
        usersPersistence = UsersDatabase.getInstance();
        companiesPersistence = CompaniesDatabase.getInstance();
        ordersPersistence = OrdersDatabase.getInstance();
        stocksPersistence = StocksDatabase.getInstance();
        userList = usersPersistence.load();
        companies = companiesPersistence.load();
        orders = ordersPersistence.load();

        tradingServer = new TradingServer(this);

        Thread thread = new Thread(prices);
        thread.start();

//        new Thread(()->{
//            for (User u : userList.getUsers()) {
//                for (Company c : companies.getCompanies()) {
//                    try {
//                        u.addStock(stocksPersistence.load(u, c));
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                }
//            }
//        }).start();

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
            e.printStackTrace();
        }

        return temporaryList;
    }

    public ArrayList<Order> getOrders() {
        return orders.getOrders();
    }

    /**
     * getting order by user
     * @param user that is getting check it
     * @return order
     */

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
            e.printStackTrace();
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
                try {
                    new Thread(orders).start();
                    ordersPersistence.save(order);
                    ordersPersistence.update(orders);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("Added order for sale");
            } else {
                System.out.println("Insufficient resources");
            }
        } else {
            if (getUser(order.getUser()).getBalance() > order.getAskingPrice()) {
                orders.AddOrder(order);
                try {
                    new Thread(orders).start();
                    ordersPersistence.save(order);
                    ordersPersistence.update(orders);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("Added order to buy");
            } else {
                System.out.println("Not enough money to place order to buy");
            }
        }

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
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException {
        userList.transferMoney(userName, amount, isWithdraw);
        usersPersistence.update(userList.getUser(userName));
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

    /**
     * gets the company by name
     * @param name name that is being compared to
     * @return company
     */

    public Company getComapnyByName(String name) {
        return companies.getCompanyByName(name);
    }

    /**
     * login for user
     * @param user user that wants login
     * @return logged in user
     * @throws Exception
     */

    @Override
    public boolean login(User user) throws Exception {
        if (!userList.userExist(user)) {
            throw new Exception("Wrong username or password");
        }


        for (int i = 0; i < companies.getCompanies().size(); i++) {
            user.addStock(stocksPersistence.load(user, companies.getCompanies().get(i)));
            System.out.println(stocksPersistence.load(user, companies.getCompanies().get(i)));
        }

        return true;
    }


    /**
     * adding registered user to the list
     * @param user user that is being added
     * @return user that is registered
     * @throws Exception
     */

    @Override
    public boolean registerUser(User user) throws Exception {
        boolean result = userList.addUser(user);
        if (result) {
            usersPersistence.save(user);
            for (int i = 0; i < user.getStocks().getSize(); i++) {
                stocksPersistence.save(user.getStocks().getStock(i), user);
                stocksPersistence.update(user.getStocks().getStock(i));
            }
        }
        return result;
    }

    @Override
    public void close() throws RemoteException {
        tradingServer.close();
    }

    @Override public void addListener(PropertyChangeListener listener)
    {
        prices.addListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener)
    {
        prices.removeListener(listener);
    }
}
