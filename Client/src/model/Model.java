package model;


import utility.observer.event.ObserverEvent;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Model is a interface for functionality for user
 */

public interface Model extends LocalSubject<String, Message> {

    /**
     * login for user
     *
     * @param user user that wants login
     * @return true
     * @throws Exception
     */

    boolean login(User user) throws Exception;


    /**
     * adding registered user to the list
     *
     * @param user user that is being added
     * @return true
     * @throws Exception
     */

    boolean registerUser(User user) throws Exception;

    /**
     * get Order by id from server
     *
     * @param uuid of order
     * @return order from server
     * @throws RemoteException
     */

    Order getOrderbyID(String uuid) throws RemoteException;

    /**
     * getting order by user
     * @param user that is getting check it
     * @return order
     */

    ArrayList<Order> getAllUserOrders(String user) throws RemoteException;

    /**
     * Closer order by UUID
     * @param uuid of order that will be closed
     * @throws RemoteException
     */
    void CloseOrder(UUID uuid) throws RemoteException;

    /**
     * getting the balance of a user
     * @param userName username of the user
     * @return userName
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

    Company getCompanyBySymbol(String symbol);

    /**
     * gets the company by name
     *
     * @param name name that is being compared to
     * @return company
     */

    Company getComapnyByName(String name);

    /**
     * adds an order
     *
     * @param order order that is getting added
     */

    void AddOrder(Order order);


    /**
     * gets the user by name
     *
     * @param name name of the user
     * @return user
     */

    User getUser(String name) throws RemoteException;

    /**
     * gets and loads users stocks
     *
     * @param name name of the user
     * @return stock/s
     */

    ArrayList<Stock> LoaduserStocks(String name) throws RemoteException;


    /**
     * gets users total stocks amount
     *
     * @param name name of the user
     * @return stock amount
     */

    Double getPriceTotal(String name) throws RemoteException;

    void receivedRemoteEvent(ObserverEvent<String, Message> event);


    void close() throws RemoteException;

}
