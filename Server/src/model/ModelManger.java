package model;

import com.sun.source.tree.CatchTree;
import javafx.application.Platform;
import mediator.TradingServer;
import persistence.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * ModelManager implements model interface and implements functionality
 */

public class ModelManger implements Model, LocalListener<String, Order> {
    private Orders orders;
    private Companies companies;
    private UserList userList;
    private Stocks stocks;
    private TradingServer tradingServer;
    private UsersPersistence usersPersistence;
    private CompaniesPersistence companiesPersistence;
    private OrdersPersistence ordersPersistence;
    private StocksPersistence stocksPersistence;
    private PropertyChangeHandler<String, Order> property;


    /**
     * Constructor initialing all the instance variables
     *
     * @throws IOException
     */

    public ModelManger() throws IOException, SQLException {
        this.property = new PropertyChangeHandler<>(this, true);
        usersPersistence = UsersDatabase.getInstance();
        companiesPersistence = CompaniesDatabase.getInstance();
        ordersPersistence = OrdersDatabase.getInstance();
        stocksPersistence = StocksDatabase.getInstance();
        userList = usersPersistence.load();
        companies = companiesPersistence.load();
        orders = ordersPersistence.load();
        stocks = stocksPersistence.loadAll();
        tradingServer = new TradingServer(this);
        orders.addListener(this);
    }

    /**
     * Checks trought all stocks finds matching stock symbol with order symbol adds amount of stocks in order to stock depending if its buying/selling
     * Updates database with newest information about orders/stocks
     * @param order
     * @throws SQLException
     */
    public void UpdateOwnedStock(Order order) throws SQLException {
        for (Stock s : stocks.getAllStocks()) {
            if (s.getSymbol().equals(order.getSymbol()) && order.getUser().equals(s.getUsername())) {
                if (!order.isSell()) {
                    if (order.getStatus().equals(Status.COMPLETED)) {
                        s.setAmount((order.getAmount() + s.getAmount()));
                        s.setPrice(order.getAskingPrice().intValue() + s.getPrice());
                    }
                    if (order.getStatus().equals(Status.CLOSED)) {
                        getUser(order.getUser()).setBalance(new Balance((int) (getUser(order.getUser()).getBalance() + order.getAskingPrice())));
                    }
                }
            }
            if (s.getSymbol().equals(order.getSymbol()) && order.getUser().equals(s.getUsername())) {
                if (order.isSell() && order.getStatus().equals(Status.OPEN)) {
                    s.setAmount((s.getAmount() - order.getAmount()));
                    s.setPrice(s.getPrice() - order.getAskingPrice().intValue());
                }
            }
            if (s.getSymbol().equals(order.getSymbol()) && order.getUser().equals(s.getUsername())) {
                if (order.isSell() && order.getStatus().equals(Status.CLOSED)) {
                    s.setAmount((s.getAmount() + order.getAmount()));
                    s.setPrice(order.getAskingPrice().intValue() + s.getPrice());
                }
            }
        }
        new Thread(() -> {
            try {
                stocksPersistence.update(stocks);
                ordersPersistence.update(orders);
                if (!orders.getOrderbyId(order)) {
                    ordersPersistence.save(order);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();

    }

    /**
     * Closes order by UUID of order
     * @param uuid of order that is closed
     */
    public void closeOrder(UUID uuid) {
        try {
            for (Order o : orders.getOrders()) {
                if (o.getOrderId().equals(uuid.toString()) && o.getStatus().equals(Status.OPEN)) {
                    o.setStatus(Status.CLOSED);
                    UpdateOwnedStock(o);
                    property.firePropertyChange("ClosingOrder", uuid.toString(), o);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * gets order by UUID
     * @param uuid of order
     * @return order with specific uuid
     */

    public Order getOrderByID(String uuid) {
        return orders.getOrderbyID(uuid);
    }

    /**
     * gets the user by name
     *
     * @param name name of the user
     * @return user
     */

    public User getUser(String name) {
        return userList.getUser(new UserName(name));
    }

    /**
     * gets and loads users stocks
     *
     * @param name name of the user
     * @return stock/s
     */

    public ArrayList<Stock> LoaduserStocks(String name) {
        ArrayList<Stock> temporaryList = new ArrayList<Stock>();
        try {
            for (Stock s : stocks.getAllStocks()) {
                if (name.equals(s.getUsername()) && s.getAmount() > 0) {
                    temporaryList.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return temporaryList;
    }

    /**
     * Gets orders as arraylist
     * @return ArrayList<Order>
     */

    public ArrayList<Order> getOrders() {
        return orders.getOrders();
    }

    /**
     * getting order by user
     *
     * @param user that is getting check it
     * @return order
     */

    public Orders getPortfolioOrders(User user) {
        return orders.getOrderByUser(user);
    }


    /**
     * gets users total stocks price
     *
     * @param name name of the user
     * @return stock amount
     */

    public Double getPriceTotal(String name) {
        double d = 0.0;
        try {
            for (Company s : companies.getCompanies()) {
                d = d + s.getCurrentPrice() * stocks.getStockByUser(name).getAmount();
            }
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * Adds order depending if is user buying/selling checking for balance and available stocks on user account,
     * Starts a new thread with orders.
     * Calls methods UpdateOwnedStock(order) to update newest stocks
     *
     * @param order that is getting added
     */

    public synchronized void AddOrder(Order order) {
        if (order.isSell()) {
            for (Stock d : LoaduserStocks(order.getUser())) {
                if (d.getAmount() >= order.getAmount() && d.getSymbol().equals(order.getSymbol()) && d.getUsername().equals(order.getUser())) {
                    orders.AddOrder(order);
                    try {
                        new Thread(orders).start();
                        UpdateOwnedStock(order);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println("Added order for sale");
                } else {
                    System.out.println("Insufficient resources");
                }
            }
        } else {
            if (getUser(order.getUser()).getBalance() > order.getAskingPrice() && order.getAmount() >= 1) {
                orders.AddOrder(order);
                try {
                    userList.getUser(new UserName(order.getUser())).setBalance(new Balance((int) getBalance(new UserName(order.getUser())) - order.getAskingPrice().intValue()));
                    new Thread(orders).start();
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("Added order to buy");
            } else {
                System.out.println("Not enough money to place order to buy or cannot buy less than 1 stock");
            }
        }

    }

    /**
     * gets the balance of the user
     *
     * @param userName username of the user
     * @return balance
     */

    @Override
    public double getBalance(UserName userName) {
        return userList.getBalance(userName);
    }

    /**
     * Withdrawing or depositing money
     *
     * @param userName   Username of the user that is transferring money
     * @param amount     amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     */

    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException {
        userList.transferMoney(userName, amount, isWithdraw);
        usersPersistence.update(userList.getUser(userName));
    }

    /**
     * gets all the companies
     *
     * @return companies
     */

    @Override
    public ArrayList<Company> getAllCompanies() {
        return companies.getCompanies();
    }

    /**
     * gets the company by symbol
     *
     * @param symbol symbol that is being compared to
     * @return company
     */

    @Override
    public Company getCompanyBySymbol(String symbol) {
        return companies.getCompanyBySymbol(symbol);
    }

    /**
     * gets the company by name
     *
     * @param name name that is being compared to
     * @return company
     */

    public Company getComapnyByName(String name) {
        return companies.getCompanyByName(name);
    }

    /**
     * login for user
     *
     * @param user user that wants login
     * @return logged in user
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
     * Gets all user orders
     * @param user whos orders will be serached
     * @return templist list of all userorders
     */

    public ArrayList<Order> getAllUserOrders(String user) {
        ArrayList<Order> templist = new ArrayList<>();
        for (Order o : orders.getUserOrders(user)) {
            templist.add(o);
        }
        return templist;
    }


    /**
     * adding registered user to the list
     *
     * @param user user that is being added
     * @return user that is registered
     * @throws Exception
     */

    @Override
    public boolean registerUser(User user) throws Exception {
        boolean result = userList.addUser(user);
        if (result) {
            usersPersistence.save(user);
            for (Company c : companies.getCompanies()) {
                stocks.addStock(new Stock(c.getSymbol(), user.getUserName().getName()));
            }
        }
        return result;
    }

    @Override
    public void close() throws RemoteException {
        tradingServer.close();
    }

    @Override
    public boolean addListener(GeneralListener<String, Order> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);

    }

    @Override
    public boolean removeListener(GeneralListener<String, Order> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);

    }

    /**
     * Waits for event of order getting completed
     * @param event
     */
    @Override
    public void propertyChange(ObserverEvent<String, Order> event) {
        Platform.runLater(() ->
        {
            try {
                if (event.getPropertyName().toString().equals("OrderCompleted")) {
                    UpdateOwnedStock(event.getValue2());
                    System.out.println(event.getValue2());
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }
}
