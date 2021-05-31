package model;

import com.sun.source.tree.CatchTree;
import javafx.application.Platform;
import mediator.TradingServer;
import persistence.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * ModelManager implements model interface and implements functionality
 */

public class ModelManger implements Model, LocalListener<String, Message> {
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
    private PriceHistoryPersistence priceHistoryPersistence;
    private PropertyChangeHandler<String, Message> property;
    private Thread threadPrices;


    /**
     * Constructor initialing all the instance variables, adds listeners and starts price thread
     *
     * @throws IOException if there occurred error with registry inside TradingServer class
     * or name in startServer method inside TradingServer is not appropriately formatted
     * @throws SQLException can be thrown to provide information on a database access error
     */

    public ModelManger() throws IOException, SQLException {
        this.property = new PropertyChangeHandler<>(this, true);
        usersPersistence = UsersDatabase.getInstance();
        companiesPersistence = CompaniesDatabase.getInstance();
        ordersPersistence = OrdersDatabase.getInstance();
        stocksPersistence = StocksDatabase.getInstance();
        priceHistoryPersistence = PriceHistoryDatabase.getInstance();
        userList = usersPersistence.load();
        companies = companiesPersistence.load();
        orders = ordersPersistence.load();
        stocks = stocksPersistence.loadAll();
        prices = priceHistoryPersistence.load();
        tradingServer = new TradingServer(this);
        orders.addListener(this);
        prices.addListener(this);
        threadPrices = new Thread(prices);
        threadPrices.start();
    }

    /**
     * Checks trough all stocks finds matching stock symbol with order symbol adds amount of stocks in order to stock depending if its buying/selling
     * Updates database with newest information about orders/stocks
     *
     * @param order
     * @throws SQLException can be thrown to provide information on a database access error
     */
    private void UpdateOwnedStock(Order order) throws SQLException {
        for (Stock s : stocks.getAllStocks()) {
            if (s.getSymbol().equals(order.getSymbol()) && order.getUser().equals(s.getUsername())) {
                if (!order.isSell()) {
                    if (order.getStatus().equals(Status.COMPLETED)) {
                        s.setPrice(Math.round(orders.getboughtPriceInStock(getUser(order.getUser()), s)*100)/100.0);
                        s.setAmount((order.getAmount() + s.getAmount()));
                    }
                    if (order.getStatus().equals(Status.CLOSED)) {
                        getUser(order.getUser()).setBalance(new Balance((int) (getUser(order.getUser()).getBalance() + order.getAskingPrice())));
                    }
                }

            }
            if (s.getSymbol().equals(order.getSymbol()) && order.getUser().equals(s.getUsername())) {
                if (order.isSell() && order.getStatus().equals(Status.OPEN)) {
                    s.setPrice(Math.round(orders.getboughtPriceInStock(getUser(order.getUser()), s)*100)/100.0);
                    s.setAmount((s.getAmount() - order.getAmount()));

                }
            }
            if (s.getSymbol().equals(order.getSymbol()) && order.getUser().equals(s.getUsername())) {
                if (order.isSell() && order.getStatus().equals(Status.CLOSED)) {
                    s.setPrice(Math.round(orders.getboughtPriceInStock(getUser(order.getUser()), s)*100)/100.0);
                    s.setAmount((s.getAmount() + order.getAmount()));

                }
            }
        }
        new Thread(() -> {
            try {
                usersPersistence.update(userList.getUser(new UserName(order.getUser())));
                stocksPersistence.update(stocks);
                ordersPersistence.update(orders);
                if (!ordersPersistence.load().getOrderbyId(order)) {
                    ordersPersistence.save(order);
                }
            } catch (Exception e) {
            }
        }).start();

    }

    /**
     * Closes order by UUID of order
     * @param uuid of order that is closed
     */
    @Override
    public void closeOrder(UUID uuid) {
        try {
            for (Order o : orders.getOrders()) {
                if (o.getOrderId().equals(uuid.toString()) && o.getStatus().equals(Status.OPEN)) {
                    o.setStatus(Status.CLOSED);
                    UpdateOwnedStock(o);
                    property.firePropertyChange("ClosingOrder", uuid.toString(), new Message(o, null));
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
    @Override
    public Order getOrderByID(String uuid) {
        return orders.getOrderbyID(uuid);
    }

    /**
     * gets the user by name
     *
     * @param name name of the user
     * @return user
     */
    @Override
    public User getUser(String name) {
        return userList.getUser(new UserName(name));
    }

    /**
     * gets and loads users stocks
     *
     * @param name name of the user
     * @return stock/s
     */
    @Override
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
     * getting order by user
     *
     * @param user that is getting check it
     * @return order
     */

    @Override
    public Orders getPortfolioOrders(User user) {
        return orders.getOrderByUser(user);
    }


    /**
     * gets users total stocks price
     *
     * @param name name of the user
     * @return stock amount
     */

    @Override
    public Double getPriceTotal(String name) {
        double d = 0.0;
        try {
            for (Company s : companies.getCompanies()) {
                d = d + s.getCurrentPrice() * stocks.getStocksByUser(name).getStockBySymbol(s.getSymbol()).getAmount();
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
     * Fires property event to views so that JavaFX window is up to date
     *
     * @param order that is getting added
     */

    @Override
    public synchronized void AddOrder(Order order) {
        if (order.isSell()) {
            for (Stock d : LoaduserStocks(order.getUser())) {
                if (d.getAmount() >= order.getAmount() && d.getSymbol().equals(order.getSymbol()) && d.getUsername().equals(order.getUser())) {
                    orders.AddOrder(order);
                    try {
                        new Thread(orders).start();
                        UpdateOwnedStock(order);
                        usersPersistence.update(userList.getUser(new UserName(order.getUser())));
                        property.firePropertyChange("balanceUpdate", (userList.getUser(new UserName(order.getUser()))).getBalance().toString(), new Message(order, null));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println("Added order for sale");
                } else {
                    System.out.println("Insufficient resources");
                }
            }
        } else {
            if (getUser(order.getUser()).getBalance() >= order.getAskingPrice() && order.getAmount() >= 1) {
                orders.AddOrder(order);
                try {
                    userList.getUser(new UserName(order.getUser())).setBalance(new Balance((int) getBalance(new UserName(order.getUser())) - order.getAskingPrice().intValue() * order.getAmount()));
                    new Thread(orders).start();
                    UpdateOwnedStock(order);
                    usersPersistence.update(userList.getUser(new UserName(order.getUser())));
                    property.firePropertyChange("balanceUpdate", (userList.getUser(new UserName(order.getUser()))).getBalance().toString(), new Message(order, null));
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
     * @param userName   Username of the user that is transferring money
     * @param amount     amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     * @throws SQLException can be thrown to provide information on a database access error
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
     * @return returns true
     * @throws Exception user that wants to login doesn't exist
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
     * @return templist list of all user orders
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
     * @param user user that is being added
     * @return returns true
     * @throws Exception if the user all ready exist
     */

    @Override
    public boolean registerUser(User user) throws Exception {
        boolean result = userList.addUser(user);
        if (result) {
            usersPersistence.save(user);
            for (Company c : companies.getCompanies()) {
                Stock s = new Stock(c.getSymbol(), user.getUserName().getName());
                stocks.addStock(s);
                stocksPersistence.save(s, user);
            }
        }
        return result;
    }

    /**
     * stops model and prices after closing gui and interrupts running price thread
     * @throws RemoteException if the remote object is not currently exported
     */
    @Override
    public void close() throws RemoteException {
        tradingServer.close();
        prices.close();
        threadPrices.interrupt();
    }


    @Override
    public boolean addListener(GeneralListener<String, Message> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);

    }

    @Override
    public boolean removeListener(GeneralListener<String, Message> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);
    }

    /**
     * Wait for company price change if there is orders will be checked if there is any valid
     * order to fulfill
     * <p>
     * Waits for event of order getting completed if there is,then user owned stocks will get updated
     *
     * @param event
     */

    @Override
    public void propertyChange(ObserverEvent<String, Message> event) {
        if (event.getPropertyName().equals("Price")) {

            for (Order o : orders.getUserOrders("Broker")) {
                if (event.getValue1().equals(o.getSymbol())) {
                    try {
                        o.setAskingPrice(BigDecimal
                                .valueOf(event.getValue2().getPriceObject().getPrice()));
                        new Thread(orders).start();
                        ordersPersistence.update(orders);
                        property.firePropertyChange(event);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }

            String symbol = event.getValue2().getPriceObject().getSymbol();
            Company company = new Company(symbol, symbol);
            company.setCurrentPrice(event.getValue2().getPriceObject().getPrice());
            try {
                priceHistoryPersistence.save(event.getValue2().getPriceObject());
                companiesPersistence.update(company);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            getCompanyBySymbol(event.getValue1()).setCurrentPrice(event.getValue2().getPriceObject().getPrice());
            property.firePropertyChange(event);


        }
        try {
            if (event.getPropertyName().equals("OrderCompleted")) {
                UpdateOwnedStock(event.getValue2().getOrder());
                System.out.println(event.getValue2());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
