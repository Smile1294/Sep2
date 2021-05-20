package model;


import utility.PropertyChangeSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Model is a interface for functionality for user
 */

public interface Model extends PropertyChangeSubject
{

    /**
     * login for user
     * @param user user that wants login
     * @return true
     * @throws Exception
     */

    boolean login(User user) throws Exception;


    /**
     * adding registered user to the list
     * @param user user that is being added
     * @return true
     * @throws Exception
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
     * @param userName Username of the user that is transferring money
     * @param amount amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     */
    void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException;

    /**
     * gets all the companies
     * @return companies
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
     * getting order by user
     * @param user that is getting check it
     * @return order
     */

    Orders getPortfolioOrders(User user);



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
     * gets users total stocks amount
     * @param name name of the user
     * @return stock amount
     */

     Double getPriceTotal(String name);


     void close() throws RemoteException;

}
