package model;


import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Model is a interface for functionality for user
 */

public interface Model extends LocalSubject<String, Message>
{
    /**
     * sets status of order to close
     * @param uuid id of order to close
     */
    void closeOrder(UUID uuid);

    /**
     * gets order by id
     * @param uuid id of order to search for
     * @return order
     */
    Order getOrderByID(String uuid);


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
     * getting the balance of a user
     * @param userName username of the user
     * @return userName
     */

    double getBalance(UserName userName);

    /**
     * Withdrawing or depositing money
     *
     * @param userName   Username of the user that is transferring money
     * @param amount     amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     * @throws SQLException can be thrown to provide information on a database access error
     */
    void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException;

    /**
     * gets all the companies
     * @return companies in arrayList
     */

    ArrayList<Company> getAllCompanies();

    /**
     * gets the company by symbol
     * @param symbol symbol that is being compared to
     * @return company
     */

    Company getCompanyBySymbol(String symbol);

    /**
     * gets the company by name
     * @param name name that is being compared to
     * @return company
     */

    Company getComapnyByName(String name);

    /**
     * adds an order
     * @param order order that is getting added
     */

    void AddOrder(Order order);

    /**
     * getting orders by user
     *
     * @param user that is getting check it
     * @return orders
     */

    Orders getPortfolioOrders(User user);

    /**
     * getting orders by name of user
     * @param name that is getting check it as string
     * @return ArrayList<order>
     */

    ArrayList<Order> getAllUserOrders(String name);


    /**
     * gets the user by name
     * @param name name of the user
     * @return user
     */

    User getUser(String name);

    /**
     * gets and loads users stocks
     * @param name name of the user
     * @return stock/s
     */

    ArrayList<Stock> LoaduserStocks(String name);


    /**
     * gets users total stocks price
     *
     * @param name name of the user
     * @return stock amount
     */

    Double getPriceTotal(String name);

    /**
     * stops model and prices after closing gui and interrupts running price thread
     * @throws RemoteException if the remote object is not currently exported
     */
    void close() throws RemoteException;

}
