package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class TradingServer is used to communicate with client
 */

public class TradingServer extends UnicastRemoteObject implements RemoteModel, LocalListener<String, Message> {
    private Model localModel;
    private PropertyChangeHandler<String, Message> property;

    /**
     * Constructor TradingServer initalizes variables
     *
     * @param model
     * @throws RemoteException
     * @throws MalformedURLException
     */

    public TradingServer(Model model) throws RemoteException, MalformedURLException {
        super();
        startRegistry();
        this.localModel = model;
        localModel.addListener(this);
        startServer();
        property = new PropertyChangeHandler<>(this, true);

    }

    /**
     * Creates Registry with port (1099)
     *
     * @throws RemoteException
     */
    private void startRegistry() throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started");
        } catch (java.rmi.server.ExportException e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }

    /**
     * prints out that server started
     *
     * @throws MalformedURLException
     * @throws RemoteException
     */

    private void startServer() throws MalformedURLException, RemoteException {
        Naming.rebind("trading", this);
        System.out.println("Server ready");
    }

    /**
     * closes server
     *
     * @throws RemoteException
     */
    @Override
    public void close() throws RemoteException {
        UnicastRemoteObject.unexportObject(this, true);
    }

    /**
     * adds order to local model
     *
     * @param order
     * @throws RemoteException
     */
    @Override
    public void AddOrder(Order order) throws RemoteException {
        localModel.AddOrder(order);
    }

    /**
     * gets company by its name
     *
     * @param name of company
     * @return Company from local model
     * @throws RemoteException
     */

    @Override
    public Company getCompanyname(String name) throws RemoteException {
        return localModel.getComapnyByName(name);
    }

    /**
     * Gets all user orders in ArrayList<Order>
     *
     * @param user of whom the orders are
     * @return ArrayList<Orders> returns list of orders of user
     * @throws RemoteException
     */

    @Override
    public ArrayList<Order> getAllUserOrers(String user) throws RemoteException {
        return localModel.getAllUserOrders(user);
    }

    /**
     * Closes order by UUID
     *
     * @param uuid of order that will be closed
     * @throws RemoteException
     */
    @Override
    public void CloseOrder(UUID uuid) throws RemoteException {
        localModel.closeOrder(uuid);
    }

    /**
     * Gets ArrayList<Stock> of users stocks
     *
     * @param name of whom stocks are
     * @return ArrayList<Stock> arraylist of stocks that user owns
     * @throws RemoteException
     */
    @Override
    public ArrayList<Stock> getAllUserStock(String name) throws RemoteException {
        return localModel.LoaduserStocks(name);
    }

    /**
     * Gets user of name
     *
     * @param name of user
     * @return User
     * @throws RemoteException
     */
    @Override
    public User getUser(String name) throws RemoteException {
        return localModel.getUser(name);
    }

    /**
     * Gets all user orders in ArrayList<Order>
     *
     * @param user of whom the orders are
     * @return ArrayList<Orders> returns list of orders of user
     * @throws RemoteException
     */

    @Override
    public ArrayList<Order> getAllUserOrders(String user) throws RemoteException {
        return localModel.getPortfolioOrders(getUser(user)).getOrders();
    }

    /**
     * gets specific order by its UUID
     *
     * @param uuid of order that will be retuned
     * @return specific order
     * @throws RemoteException
     */
    @Override
    public Order getOrderbyId(String uuid) throws RemoteException {
        return localModel.getOrderByID(uuid);
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
        return localModel.login(user);
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
        return localModel.registerUser(user);
    }


    /**
     * gets the balance of the user
     *
     * @param userName username of the user
     * @return balance
     */
    @Override
    public double getBalance(UserName userName) {
        return localModel.getBalance(userName);
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
        localModel.transferMoney(userName, amount, isWithdraw);
    }
    /**
     * gets users total stocks amount
     *
     * @param name name of the user
     * @return stock amount
     */
    @Override
    public Double getPriceTotal(String name) throws RemoteException {
        return localModel.getPriceTotal(name);
    }

    /**
     * gets all the companies
     *
     * @return companies
     * @throws RemoteException
     */
    @Override
    public ArrayList<Company> getAllCompanies() throws RemoteException {
        return localModel.getAllCompanies();
    }
    /**
     * gets the company by symbol
     *
     * @param symbol symbol that is being compared to
     * @return company
     */
    @Override
    public Company getCompanyBySymbol(String symbol) throws RemoteException {
        return localModel.getCompanyBySymbol(symbol);
    }

    /**
     * @param listener
     * @param propertyNames
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean addListener(GeneralListener<String, Message> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }

    /**
     * @param listener
     * @param propertyNames
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean removeListener(GeneralListener<String, Message> listener, String... propertyNames) throws RemoteException {
        return property.removeListener(listener, propertyNames);
    }

    /**
     * @param event
     */

    @Override public void propertyChange(ObserverEvent<String, Message> event)
    {
        property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
    }
}
