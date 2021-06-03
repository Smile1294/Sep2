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
     * Constructor TradingServer initializes variables
     *
     * @param model
     * @throws RemoteException if failed to export object threw super() or error with registry occur
     * @throws MalformedURLException if name in startServer is not appropriately formatted
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
     * Creates Registry with port (1099) for communication between client and server
     * port 1099 is used for client to connect
     *
     * @throws RemoteException if the registry could not be exported
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
     * @throws MalformedURLException if the name is not appropriately formatted
     * @throws RemoteException if registry could not be contacted
     */
    private void startServer() throws MalformedURLException, RemoteException {
        Naming.rebind("trading", this);
        System.out.println("Server ready");
    }

    /**
     * closes server
     *
     * @throws RemoteException if the remote object is not currently exported
     */
    public void close() throws RemoteException {
        UnicastRemoteObject.unexportObject(this, true);
    }

    /**
     * adds order to local model from client
     *
     * @param order that is added to list in server
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    @Override
    public void AddOrder(Order order) throws RemoteException {
        localModel.AddOrder(order);
    }

    /**
     * gets company by its name for client
     * @param name of company
     * @return Company from local model
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */

    @Override
    public Company getCompanyname(String name) throws RemoteException {
        return localModel.getComapnyByName(name);
    }

    /**
     * Gets all user orders in ArrayList<Order>
     *
     * @param user of whom the orders are
     * @return ArrayList<Orders> list of orders of user
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */

    @Override
    public ArrayList<Order> getAllUserOrers(String user) throws RemoteException {
        return localModel.getAllUserOrders(user);
    }

    /**
     * Closes order by UUID from client
     * @param uuid of order that will be closed
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
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
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    @Override
    public ArrayList<Stock> getAllUserStock(String name) throws RemoteException {
        return localModel.LoaduserStocks(name);
    }

    /**
     * Gets user object by name of user
     * @param name of user that will be returned
     * @return User
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    @Override
    public User getUser(String name) throws RemoteException {
        return localModel.getUser(name);
    }

    /**
     * Gets all user orders in ArrayList<Order>
     * @param user of whos orders will be returned
     * @return ArrayList<Orders> returns list of orders of user
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */

    @Override
    public ArrayList<Order> getAllUserOrders(String user) throws RemoteException {
        return localModel.getPortfolioOrders(getUser(user)).getOrders();
    }

    /**
     * gets specific order by its UUID
     * @param uuid of order that will be returned
     * @return specific order
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    @Override
    public Order getOrderbyId(String uuid) throws RemoteException {
        return localModel.getOrderByID(uuid);
    }

    /**
     * login for user
     * @param user that wants login
     * @return returns true
     * @throws Exception user that wants to login doesn't exist
     */
    @Override
    public boolean login(User user) throws Exception {
        return localModel.login(user);
    }


    /**
     * adding registered user to the list
     *
     * @param user user that is being added
     * @return returns true
     * @throws Exception if the user all ready exist
     */
    @Override
    public boolean registerUser(User user) throws Exception {
        return localModel.registerUser(user);
    }


    /**
     * gets the balance of the user
     *
     * @param userName username of the user
     * @return balance of user
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
     * @throws SQLException can be thrown to provide information on a database access error
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
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    @Override
    public Double getPriceTotal(String name) throws RemoteException {
        return localModel.getPriceTotal(name);
    }

    /**
     * gets all the companies
     *
     * @return companies
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
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
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    @Override
    public Company getCompanyBySymbol(String symbol) throws RemoteException {
        return localModel.getCompanyBySymbol(symbol);
    }

    @Override
    public boolean addListener(GeneralListener<String, Message> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }


    @Override
    public boolean removeListener(GeneralListener<String, Message> listener, String... propertyNames) throws RemoteException {
        return property.removeListener(listener, propertyNames);
    }


    @Override public void propertyChange(ObserverEvent<String, Message> event)
    {
        property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
    }
}
