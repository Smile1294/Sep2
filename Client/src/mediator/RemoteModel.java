package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject<String, Order> {
    boolean login(User user) throws Exception;
    boolean registerUser(User user) throws Exception;
    double getBalance(UserName userName) throws RemoteException;
    void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException, RemoteException;
    Double getPriceTotal(String name) throws RemoteException;
    ArrayList<Company> getAllCompanies() throws RemoteException;
    Company getCompanyBySymbol(String symbol) throws RemoteException;
    void close() throws RemoteException;
    void AddOrder(Order order) throws RemoteException;
    void CloseOrder(Order order) throws RemoteException;
}
