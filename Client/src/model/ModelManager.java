package model;

import mediator.LocalClientModel;
import mediator.TradingClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * ModelManager implements model interface and implements functionality
 */

public class ModelManager implements Model {
    private LocalClientModel tradingClient;
    private PropertyChangeHandler<String, Message> property;


    /**
     * Constructor initialing all the instance variables
     *
     * @throws IOException
     */
    public ModelManager() throws IOException, SQLException {
        this.property = new PropertyChangeHandler<>(this, true);
        this.tradingClient = new TradingClient("localhost", this);
    }


    public void CloseOrder(UUID uuid) throws RemoteException {
        if (getOrderbyID(uuid.toString()).getStatus().equals(Status.OPEN)) {
            tradingClient.CloseOrder(uuid);
            property.firePropertyChange("ClosingOrder", uuid.toString(), new Message(getOrderbyID(uuid.toString()), null));
        }

    }

    /**
     * getting order by user
     *
     * @param user that is getting check it
     * @return order
     */

    public ArrayList<Order> getAllUserOrders(String user) throws RemoteException {
        return tradingClient.getAllUserOrders(user);
    }

    public Order getOrderbyID(String uuid) throws RemoteException {
        return tradingClient.getOrderbyID(uuid);
    }


    /**
     * gets the user by name
     *
     * @param name name of the user
     * @return user
     */
    public User getUser(String name) throws RemoteException {
        return tradingClient.getUser(name);
    }

    /**
     * gets and loads users stocks
     *
     * @param name name of the user
     * @return stock/s
     */

    public ArrayList<Stock> LoaduserStocks(String name) throws RemoteException {
        return tradingClient.getAllUserStock(name);
    }

    public ArrayList<Order> getOrders() {
        return null;
    }


    /**
     * gets users total stocks amount
     *
     * @param name name of the user
     * @return stock amount
     */

    public Double getPriceTotal(String name) throws RemoteException {
        return tradingClient.getPriceTotal(name);
    }

    @Override
    public void receivedRemoteEvent(ObserverEvent<String, Message> event) {
        property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
    }

    /**
     * adds an order
     *
     * @param order order that is getting added
     */

    public void AddOrder(Order order) {
        try {
            tradingClient.AddOrder(order);
            property.firePropertyChange("balanceUpdate", (getUser((order.getUser()))).getBalance().toString(), new Message(order,null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * gets the balance of the user
     *
     * @param userName username of the user
     * @return balance
     */

    @Override
    public double getBalance(UserName userName) throws RemoteException {
        return tradingClient.getBalance(userName);
    }


    /**
     * Withdrawing or depositing money
     *
     * @param userName   Username of the user that is transferring money
     * @param amount     amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     */

    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException, RemoteException {
        tradingClient.transferMoney(userName, amount, isWithdraw);
    }

    /**
     * gets all the companies
     *
     * @return companies
     */

    @Override
    public ArrayList<Company> getAllCompanies() throws RemoteException {
        return tradingClient.getAllCompanies();
    }

    /**
     * gets the company by symbol
     *
     * @param symbol symbol that is being compared to
     * @return company
     */

    @Override
    public Company getCompanyBySymbol(String symbol) {
        Company company = null;
        try {
            company = tradingClient.getCompanyBySymbol(symbol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return company;
    }

    /**
     * gets the company by name
     *
     * @param name name that is being compared to
     * @return company
     */

    public Company getComapnyByName(String name) {
        return tradingClient.getCompanyname(name);
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
        return tradingClient.login(user);
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
        boolean result = tradingClient.registerUser(user);
        return result;
    }

    @Override
    public void close() throws RemoteException {
        tradingClient.close();
    }


    @Override
    public boolean addListener(GeneralListener<String, Message> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);

    }

    @Override
    public boolean removeListener(GeneralListener<String, Message> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);

    }

}
