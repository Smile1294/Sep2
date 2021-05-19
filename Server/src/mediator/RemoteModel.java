package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 */
public interface RemoteModel extends RemoteSubject<String, Order> {

    /**
     * login for user
     *
     * @param user user that wants login
     * @return logged in user
     * @throws Exception
     */
    boolean login(User user) throws Exception;


    /**
     * adding registered user to the list
     *
     * @param user user that is being added
     * @return user that is registered
     * @throws Exception
     */
    boolean registerUser(User user) throws Exception;

    /**
     * gets the balance of the user
     *
     * @param userName username of the user
     * @return balance
     */

    double getBalance(UserName userName) throws RemoteException;

    /**
     * Withdrawing or depositing money
     *
     * @param userName   Username of the user that is transferring money
     * @param amount     amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     */
    void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException, RemoteException;


    /**
     * gets users total stocks price
     *
     * @param name name of the user
     * @return stock amount
     */

    Double getPriceTotal(String name) throws RemoteException;

    /**
     * gets all the companies
     *
     * @return companies
     */

    ArrayList<Company> getAllCompanies() throws RemoteException;

    /**
     * gets the company by symbol
     *
     * @param symbol symbol that is being compared to
     * @return company
     */

    Company getCompanyBySymbol(String symbol) throws RemoteException;

    void close() throws RemoteException;

    /**
     * adds order
     *
     * @param order that will be added
     * @throws RemoteException
     */

    void AddOrder(Order order) throws RemoteException;

    /**
     * gets the company by name
     *
     * @param name name that is being compared to
     * @return company
     */

    Company getCompanyname(String name) throws RemoteException;

    /**
     * Gets all user orders
     *
     * @param user whos orders will be serached
     * @return templist list of all userorders
     */

    ArrayList<Order> getAllUserOrers(String user) throws RemoteException;

    /**
     * Closes order by UUID of order
     *
     * @param uuid of order that is closed
     */

    void CloseOrder(UUID uuid) throws RemoteException;

    /**
     * Gets ArrayList<Stock> of users stocks
     *
     * @param name of whom stocks are
     * @return ArrayList<Stock> arraylist of stocks that user owns
     * @throws RemoteException
     */

    ArrayList<Stock> getAllUserStock(String name) throws RemoteException;

    /**
     * gets the user by name
     *
     * @param name name of the user
     * @return user
     */
    User getUser(String name) throws RemoteException;

    /**
     * Gets all user orders
     *
     * @param user whos orders will be serached
     * @return templist list of all userorders
     */

    ArrayList<Order> getAllUserOrders(String user) throws RemoteException;

    /**
     * gets order by UUID
     *
     * @param uuid of order
     * @return order with specific uuid
     */
    Order getOrderbyId(String uuid) throws RemoteException;


}
