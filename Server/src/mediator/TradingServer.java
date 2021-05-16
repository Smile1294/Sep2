package mediator;

import model.Company;
import model.Model;
import model.User;
import model.UserName;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class TradingServer extends UnicastRemoteObject implements RemoteModel, LocalListener<String, String> {
    private Model localModel;
    private PropertyChangeHandler<String, String> property;

    public TradingServer(Model model) throws RemoteException, MalformedURLException {
        super();
        startRegistry();
        this.localModel = model;
//        localModel.addListener(this,"Message","Log","Login","Register");
        startServer();
        property = new PropertyChangeHandler<>(this,true);
    }

    private void startRegistry() throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started");
        }catch (java.rmi.server.ExportException e){
            System.err.println("Server exception: "+e);
            e.printStackTrace();
        }
    }

    private void startServer() throws MalformedURLException, RemoteException {
        Naming.rebind("trading",this);
        System.out.println("Server ready");
    }

    @Override
    public void close() throws RemoteException {
        UnicastRemoteObject.unexportObject(this,true);
    }

    @Override
    public boolean login(User user) throws Exception {
        return localModel.login(user);
    }

    @Override
    public boolean registerUser(User user) throws Exception {
        return localModel.registerUser(user);
    }

    @Override
    public double getBalance(UserName userName) {
        return localModel.getBalance(userName);
    }

    @Override
    public void transferMoney(UserName userName, double amount, boolean isWithdraw) throws SQLException {
        localModel.transferMoney(userName,amount,isWithdraw);
    }

    @Override
    public Double getPriceTotal(String name) throws RemoteException {
        return localModel.getPriceTotal(name);
    }

    @Override
    public ArrayList<Company> getAllCompanies() throws RemoteException {
        return localModel.getAllCompanies();
    }

    @Override
    public Company getCompanyBySymbol(String symbol) throws RemoteException {
        return localModel.getCompanyBySymbol(symbol);
    }

    @Override
    public void propertyChange(ObserverEvent<String, String> event) {

    }

    @Override
    public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
        return false;
    }

    @Override
    public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
        return false;
    }
}