package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public interface RemoteModel extends RemoteSubject<String, Message> {

    /**
     * Method used to login the user into the server. It works by providing a user class that contains username/password , and is expected to return
     * a boolean that has that either grants access to system or denyies access to system. depending on that if user is in database or not.
     *
     * @param user that will be serached in database
     * @return boolean
     * @throws Exception
     */
    boolean login(User user) throws Exception;

    /**
     * Mathod used to register user to server/database, It works by providing user class to server that will be added to database
     *
     * @param user that will be registred
     * @return bollean depending if the information about user are valid
     * @throws Exception
     */
    boolean registerUser(User user) throws Exception;

    /**
     * gets balance of user by username of user
     *
     * @param userName of user that whos balance will be returned
     * @return users balance
     * @throws RemoteException
     */
    double getBalance(UserName userName) throws RemoteException;

    /**
     * Withdrawing or depositing money
     *
     * @param userName   Username of the user that is transferring money
     * @param amount     amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     * @throws SQLException
     * @throws RemoteException
     */
    void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException, RemoteException;

    /**
     * gets users total stocks price
     *
     * @param name name of the user
     * @return stock amount
     * @throws RemoteException
     */
    Double getPriceTotal(String name) throws RemoteException;

    /**
     * gets all the companies
     *
     * @return companies
     * @throws RemoteException
     */
    ArrayList<Company> getAllCompanies() throws RemoteException;

    /**
     * gets the company by symbol
     *
     * @param symbol symbol that is being compared to
     * @return company
     * @throws RemoteException
     */
    Company getCompanyBySymbol(String symbol) throws RemoteException;

    /**
     * closes connection between client and server
     *
     * @throws RemoteException
     */
    void close() throws RemoteException;

    /**
     * Creates order and sends it to server,server will then validate this request.
     *
     * @param order that will be sent toserver
     * @throws RemoteException
     */
    void AddOrder(Order order) throws RemoteException;

    /**
     * Closes order by UUID of order
     *
     * @param uuid of order that is closed
     * @throws RemoteException
     */
    void CloseOrder(UUID uuid) throws RemoteException;

    /**
     * gets company by its name
     *
     * @param name of company
     * @return Company
     */
    Company getCompanyname(String name);

    /**
     * Gets all stocks that user owns
     *
     * @param name of user whos stocks will be returned
     * @return Stocks
     * @throws RemoteException
     */
    ArrayList<Stock> getAllUserStock(String name) throws RemoteException;

    /**
     * Gets all orders that user made
     *
     * @param user of whos orders will be returned
     * @return orders
     * @throws RemoteException
     */
    ArrayList<Order> getAllUserOrders(String user) throws RemoteException;

    /**
     * gets order by UUID
     *
     * @param uuid of order
     * @return order with specific uuid
     * @throws RemoteException
     */
    Order getOrderbyId(String uuid) throws RemoteException;

    /**
     * gets the user by name
     *
     * @param name name of the user
     * @return user
     * @throws RemoteException
     */
    User getUser(String name) throws RemoteException;


}
