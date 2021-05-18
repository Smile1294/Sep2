package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class TradingClient extends UnicastRemoteObject implements LocalClientModel, RemoteListener<String, Order> {
    private RemoteModel server;
    private Model localModel;

    public TradingClient(String host, Model localModel) throws RemoteException {
        super();
        this.localModel = localModel;
        try {
            server = (RemoteModel) Naming.lookup("rmi://" + host + ":1099/trading");
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
        server.addListener(this);

    }
    public void CloseOrder(Order order) throws RemoteException {
        server.CloseOrder(order);
    }

    @Override
    public void AddOrder(Order order) throws RemoteException {
        server.AddOrder(order);
    }
    @Override
    public void propertyChange(ObserverEvent<String, Order> event) throws RemoteException {
        localModel.receivedRemoteEvent(event);
    }

    @Override
    public boolean login(User user) throws Exception {
        return server.login(user);
    }

    @Override
    public boolean registerUser(User user) throws Exception {
        return server.registerUser(user);
    }

    @Override
    public double getBalance(UserName userName) throws RemoteException {
        return server.getBalance(userName);
    }

    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException, RemoteException {
        server.transferMoney(userName, amount, isWithdraw);
    }

    @Override
    public Double getPriceTotal(String name) throws RemoteException {
        return server.getPriceTotal(name);
    }

    @Override
    public ArrayList<Company> getAllCompanies() throws RemoteException {
        return server.getAllCompanies();
    }

    @Override
    public Company getCompanyBySymbol(String symbol) throws RemoteException {
        return server.getCompanyBySymbol(symbol);
    }

    @Override
    public void close() throws RemoteException {
        UnicastRemoteObject.unexportObject(this, true);
    }


}
