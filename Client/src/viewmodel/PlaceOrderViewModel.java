package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.UUID;

/**
 * PlaceOrderViewModel is class for functionality of login view
 */

public class PlaceOrderViewModel implements LocalListener<String, Order> {
    private Model model;
    private SimpleStringProperty balance;
    private ObservableList<String> list;
    private SimpleIntegerProperty price;
    private SimpleIntegerProperty amount;
    private SimpleStringProperty companyName;
    private ViewState viewState;

    /**
     * Constructor that is initialising all the instance variables
     *
     * @param model     model for functionality
     * @param viewState viewState state of the account
     */

    public PlaceOrderViewModel(Model model, ViewState viewState) {
        model.addListener(this);
        this.balance = new SimpleStringProperty();
        this.companyName = new SimpleStringProperty();
        this.amount = new SimpleIntegerProperty();
        this.price = new SimpleIntegerProperty();
        list = FXCollections.observableArrayList();
        this.model = model;
        this.viewState = viewState;
    }

    public void reset() {
        price.setValue(0);
        amount.setValue(0);
        getSelectedCompany();
    }


    /**
     * If user is comming from view of company,viewstate contains slected comapny and it will be preslected for user in view
     *
     * @return String name of company
     */

    public String getSelectedCompany() {
        if (viewState.getSelectedSymbol() != null) {
            companyName.setValue(model.getCompanyBySymbol(viewState.getSelectedSymbol()).getName());
            return companyName.get();
        }
        return "";
    }

    /**
     * gets companies that user wants
     *
     * @return list of companies
     */

    public ObservableList<String> getStockChoice() throws RemoteException {
        for (int i = 0; i < model.getAllCompanies().size(); i++) {
            list.add(model.getAllCompanies().get(i).getName());
        }
        return list;
    }

    /**
     * gets balance
     *
     * @return balance
     */

    public SimpleStringProperty balanceProperty() throws RemoteException {
        return balance = new SimpleStringProperty(String.valueOf(model.getBalance(viewState.getUserName())));
    }

    /**
     * adding an order to buy
     *
     * @param nameofcompany name of the company that we want to get order from
     * @throws Exception
     */

    public synchronized void buy(String nameofcompany) throws Exception {
        model.AddOrder(new Order(false, BigDecimal.valueOf(price.get()), amount.get(), viewState.getUserName().getName(), Status.OPEN, model.getComapnyByName(nameofcompany).getSymbol()));
    }

    /**
     * adding an order to sell
     *
     * @param nameofcompany name of the company that we want to get order from
     */

    public synchronized void sell(String nameofcompany) {
        model.AddOrder(new Order(true, BigDecimal.valueOf(price.get()), amount.get(), viewState.getUserName().getName(), Status.OPEN, model.getComapnyByName(nameofcompany).getSymbol()));
    }

    /**
     * gets the amount
     *
     * @return amount
     */

    public SimpleIntegerProperty getAmount() {
        return amount;
    }

    /**
     * gets price
     *
     * @return price
     */

    public SimpleIntegerProperty getPrice() {
        return price;
    }


    @Override
    public void propertyChange(ObserverEvent<String, Order> event) {
        Platform.runLater(() ->
        {
            try {
                if (event.getPropertyName().equals("balanceUpdate")) {
                    balance.setValue(event.getValue1());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    public boolean Back() {
        return viewState.isFromAccountView();
    }
}