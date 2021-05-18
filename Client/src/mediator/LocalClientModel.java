package mediator;

import model.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public interface LocalClientModel {
    boolean login(User user) throws Exception;

    boolean registerUser(User user) throws Exception;

    double getBalance(UserName userName) throws RemoteException;

    void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException, RemoteException;

    Double getPriceTotal(String name) throws RemoteException;

    ArrayList<Company> getAllCompanies() throws RemoteException;

    Company getCompanyBySymbol(String symbol) throws RemoteException;

    void close() throws RemoteException;

    void AddOrder(Order order) throws RemoteException;

    void CloseOrder(UUID uuid) throws RemoteException;

    Company getCompanyname(String name);

    ArrayList<Stock> getAllUserStock(String name) throws RemoteException;

    ArrayList<Order> getAllUserOrders(String user) throws RemoteException;

    Order getOrderbyID(String uuid) throws RemoteException;

    User getUser(String name) throws RemoteException;


}
