package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class TradingClient extends UnicastRemoteObject implements LocalClientModel, RemoteListener<String, Message> {
    private RemoteModel server;
    private Model localModel;

    public TradingClient(String host, Model localModel) throws RemoteException {
        super();
        this.localModel = localModel;
        try {
            server = (RemoteModel) Naming.lookup("rmi://" + host + ":1099/trading");
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
        server.addListener(this );
    }
    /**
     * gets the user by name
     *
     * @param name name of the user
     * @return user
     * @throws RemoteException
     */
    @Override
    public User getUser(String name) throws RemoteException {
        return server.getUser(name);
    }
    /**
     * Closes order by UUID of order
     *
     * @param uuid of order that is closed
     * @throws RemoteException
     */
    @Override
    public void CloseOrder(UUID uuid) throws RemoteException {
        server.CloseOrder(uuid);
    }

    /**
     * gets company by its name
     *
     * @param name of company
     * @return Company
     */

    @Override
    public Company getCompanyname(String name) {
        return server.getCompanyname(name);
    }

    /**
     * Gets all stocks that user owns
     *
     * @param name of user whos stocks will be returned
     * @return Stocks
     * @throws RemoteException
     */
    @Override
    public ArrayList<Stock> getAllUserStock(String name) throws RemoteException {
        return server.getAllUserStock(name);
    }

    /**
     * Creates order and sends it to server,server will then validate this request.
     *
     * @param order that will be sent toserver
     * @throws RemoteException
     */
    @Override
    public void AddOrder(Order order) throws RemoteException {
        server.AddOrder(order);
    }

    /**
     * Gets all orders that user made
     *
     * @param user of whos orders will be returned
     * @return orders
     * @throws RemoteException
     */
    @Override
    public ArrayList<Order> getAllUserOrders(String user) throws RemoteException {
        return server.getAllUserOrders(user);
    }

    /**
     * gets order by UUID
     *
     * @param uuid of order
     * @return order with specific uuid
     * @throws RemoteException
     */
    @Override
    public Order getOrderbyID(String uuid) throws RemoteException {
        return server.getOrderbyId(uuid);
    }

    /**
     * Method used to login the user into the server. It works by providing a user class that contains username/password , and is expected to return
     * a boolean that has that either grants access to system or denyies access to system. depending on that if user is in database or not.
     *
     * @param user that will be serached in database
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean login(User user) throws Exception {
        return server.login(user);
    }

    /**
     * Mathod used to register user to server/database, It works by providing user class to server that will be added to database
     *
     * @param user that will be registred
     * @return bollean depending if the information about user are valid
     * @throws Exception
     */
    @Override
    public boolean registerUser(User user) throws Exception {
        return server.registerUser(user);
    }

    /**
     * gets balance of user by username of user
     *
     * @param userName of user that whos balance will be returned
     * @return users balance
     * @throws RemoteException
     */
    @Override
    public double getBalance(UserName userName) throws RemoteException {
        return server.getBalance(userName);
    }

    /**
     * Withdrawing or depositing money
     *
     * @param userName   Username of the user that is transferring money
     * @param amount     amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     * @throws SQLException
     * @throws RemoteException
     */
    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException, RemoteException {
        server.transferMoney(userName, amount, isWithdraw);
    }

    /**
     * gets users total stocks price
     *
     * @param name name of the user
     * @return stock amount
     * @throws RemoteException
     */
    @Override
    public Double getPriceTotal(String name) throws RemoteException {
        return server.getPriceTotal(name);
    }

    /**
     * gets all the companies
     *
     * @return companies
     * @throws RemoteException
     */

    @Override
    public ArrayList<Company> getAllCompanies() throws RemoteException {
        return server.getAllCompanies();
    }

    /**
     * gets the company by symbol
     *
     * @param symbol symbol that is being compared to
     * @return company
     * @throws RemoteException
     */
    @Override
    public Company getCompanyBySymbol(String symbol) throws RemoteException {
        return server.getCompanyBySymbol(symbol);
    }

    /**
     * closes connection between client and server
     *
     * @throws RemoteException
     */
    @Override
    public void close() throws RemoteException {
        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public void propertyChange(ObserverEvent<String, Message> event) {
        localModel.receivedRemoteEvent(event);
    }
}
