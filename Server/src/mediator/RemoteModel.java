package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * RemoteModel interface represents remote interface used for rmi communication
 */
public interface RemoteModel extends RemoteSubject<String, Message> {

    /**
     * login for user
     *
     * @param user user that wants login
     * @return returns true
     * @throws Exception user that wants to login doesn't exist
     */
    boolean login(User user) throws Exception;


    /**
     * adding registered user to the list
     *
     * @param user user that is being added
     * @return returns true
     * @throws Exception if the user all ready exist
     */
    boolean registerUser(User user) throws Exception;

    /**
     * gets the balance of the user
     *
     * @param userName username of the user
     * @return balance
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
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     * @throws SQLException can be thrown to provide information on a database access error
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
     * closes server
     *
     * @throws RemoteException if the remote object is not currently exported
     */
    void close() throws RemoteException;

    /**
     * adds order to local model from client
     *
     * @param order that is added to list in server
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */

    void AddOrder(Order order) throws RemoteException;

    /**
     * gets company by its name for client
     * @param name of company
     * @return Company from local model
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */

    Company getCompanyname(String name) throws RemoteException;

    /**
     * Gets all user orders in ArrayList<Order>
     *
     * @param user of whom the orders are
     * @return ArrayList<Orders> list of orders of user
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */

    ArrayList<Order> getAllUserOrers(String user) throws RemoteException;

    /**
     * Closes order by UUID from client
     * @param uuid of order that will be closed
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */

    void CloseOrder(UUID uuid) throws RemoteException;

    /**
     * Gets ArrayList<Stock> of users stocks
     *
     * @param name of whom stocks are
     * @return ArrayList<Stock> arraylist of stocks that user owns
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */

    ArrayList<Stock> getAllUserStock(String name) throws RemoteException;

    /**
     * gets user object by name of user
     * @param name of user that will be returned
     * @return User
     * @throws RemoteException can be thrown for number of communication-related exceptions
     * that may occur during the execution of a remote method call
     */
    User getUser(String name) throws RemoteException;

    /**
     * Gets all user orders in ArrayList<Order>
     * @param user of whos orders will be returned
     * @return ArrayList<Orders> returns list of orders of user
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
    Order getOrderbyId(String uuid) throws RemoteException;


}
