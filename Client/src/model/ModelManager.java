package model;

import mediator.LocalClientModel;
import mediator.TradingClient;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ModelManager implements model interface and implements functionality
 */

public class ModelManager implements Model {
    private LocalClientModel tradingClient;


    /**
     * Constructor initialing all the instance variables
     * @throws IOException
     */
    public ModelManager() throws IOException, SQLException {
        this.tradingClient = new TradingClient("localhost",this);




    }


    /**
     * gets the user by name
     * @param name name of the user
     * @return user
     */
    public User getUser(String name) {
        return null;
    }

    /**
     * gets and loads users stocks
     * @param name name of the user
     * @return stock/s
     */

    public ArrayList<Stock> LoaduserStocks(String name) {
        return null;
    }

    public ArrayList<Order> getOrders() {
        return null;
    }

    /**
     * getting order by user
     * @param user that is getting check it
     * @return order
     */

    public ArrayList<Order> getPortfolioOrders(User user) {
        return null;
    }


    /**
     * gets users total stocks amount
     * @param name name of the user
     * @return stock amount
     */

    public Double getPriceTotal(String name) throws RemoteException {
        return tradingClient.getPriceTotal(name);
    }

    /**
     * adds an order
     * @param order order that is getting added
     */

//    public void AddOrder(Order order) {
//
//        if (order.isSell()) {
//            if (getUser(order.getUser()).getStocks().getStockBySymbol(order.getSymbol()).getAmount() > order.getAmount()) {
//                orders.AddOrder(order);
//                try {
//                    new Thread(orders).start();
//                    ordersPersistence.save(order);
//                    ordersPersistence.update(orders);
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//                System.out.println("Added order for sale");
//            } else {
//                System.out.println("Insufficient resources");
//            }
//        } else {
//            if (getUser(order.getUser()).getBalance() > order.getAskingPrice()) {
//                orders.AddOrder(order);
//                try {
//                    new Thread(orders).start();
//                    ordersPersistence.save(order);
//                    ordersPersistence.update(orders);
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//                System.out.println("Added order to buy");
//            } else {
//                System.out.println("Not enough money to place order to buy");
//            }
//        }
//
//    }

    /**
     * gets the balance of the user
     * @param userName username of the user
     * @return balance
     */

    @Override
    public double getBalance(UserName userName) throws RemoteException {
        return tradingClient.getBalance(userName);
    }

    /**
     * Withdrawing or depositing money
     * @param userName Username of the user that is transferring money
     * @param amount amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     */

    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException, RemoteException {
        tradingClient.transferMoney(userName, amount, isWithdraw);
    }

    /**
     * gets all the companies
     * @return companies
     */

    @Override
    public ArrayList<Company> getAllCompanies() throws RemoteException {
        return tradingClient.getAllCompanies();
    }

    /**
     * gets the company by symbol
     * @param symbol symbol that is being compared to
     * @return company
     */

    @Override
    public Company getCompanyBySymbol(String symbol) {
        Company company = null;
        try {
            company = tradingClient.getCompanyBySymbol(symbol);
        } catch (Exception e){
            e.printStackTrace();
        }
        return company;
    }

    /**
     * gets the company by name
     * @param name name that is being compared to
     * @return company
     */

    public Company getComapnyByName(String name) {
        return null;
    }

    /**
     * login for user
     * @param user user that wants login
     * @return logged in user
     * @throws Exception
     */

    @Override
    public boolean login(User user) throws Exception {
        return tradingClient.login(user);
    }


    /**
     * adding registered user to the list
     * @param user user that is being added
     * @return user that is registered
     * @throws Exception
     */

    @Override
    public boolean registerUser(User user) throws Exception {
        boolean result = tradingClient.registerUser(user);
        return result;
    }

    @Override
    public void close() throws RemoteException {
        tradingClient.close();
    }

}
