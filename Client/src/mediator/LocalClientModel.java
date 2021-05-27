package mediator;

import model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * LocalClientModel represent interface used for client side
 */
public interface LocalClientModel {
    /**
     * Method used to login the user into the server. It works by providing a user class that contains username/password , and is expected to return
     * a boolean that has that either grants access to system or denyies access to system. depending on that if user is in database or not.
     *
     * @param user that will be serached in database
     * @return boolean
     * @throws Exception user that wants to login doesn't exist
     */
    boolean login(User user) throws Exception;

    /**
     * Mathod used to register user to server/database, It works by providing user class to server that will be added to database
     *
     * @param user that will be registred
     * @return boolean depending if the information about user are valid
     * @throws Exception if the user all ready exist
     */
    boolean registerUser(User user) throws Exception;

    /**
     * gets balance of user by username of user
     *
     * @param userName of user that whos balance will be returned
     * @return users balance
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */

    double getBalance(UserName userName) throws RemoteException;

    /**
     * Withdrawing or depositing money
     *
     * @param userName   Username of the user that is transferring money
     * @param amount     amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     * @throws SQLException can be thrown to provide information on a database access error
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException, RemoteException;

    /**
     * gets users total stocks price
     *
     * @param name name of the user
     * @return stock amount
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    Double getPriceTotal(String name) throws RemoteException;

    /**
     * gets all the companies
     *
     * @return companies
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    ArrayList<Company> getAllCompanies() throws RemoteException;


    /**
     * gets the company by symbol
     *
     * @param symbol symbol that is being compared to
     * @return company
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    Company getCompanyBySymbol(String symbol) throws RemoteException;

    /**
     * closes connection between client and server
     *
     * @throws RemoteException if the remote object is not currently exported
     */
    void close() throws RemoteException;

    /**
     * Creates order and sends it to server,server will then validate this request.
     *
     * @param order that will be sent toserver
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    void AddOrder(Order order) throws RemoteException;


    /**
     * Closes order by UUID of order
     *
     * @param uuid of order that is closed
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
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
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    ArrayList<Stock> getAllUserStock(String name) throws RemoteException;

    /**
     * Gets all orders that user made
     *
     * @param user of whos orders will be returned
     * @return orders
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    ArrayList<Order> getAllUserOrders(String user) throws RemoteException;

    /**
     * gets order by UUID
     *
     * @param uuid of order
     * @return order with specific uuid
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    Order getOrderbyID(String uuid) throws RemoteException;


    /**
     * gets the user by name
     *
     * @param name name of the user
     * @return user
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    User getUser(String name) throws RemoteException;


}
