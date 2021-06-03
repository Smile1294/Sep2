package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
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

public class PlaceOrderViewModel implements LocalListener<String, Message> {
    private Model model;
    private StringProperty balance;
    private ObservableList<String> list;
    private StringProperty currentprice;
    private DoubleProperty price;
    private IntegerProperty amount;
    private StringProperty currentCompanySelected;
    private StringProperty companyName;
    private ViewState viewState;

    /**
     * Constructor that is initialising all the instance variables
     *
     * @param model     model for functionality
     * @param viewState viewState state of the account
     */

    public PlaceOrderViewModel(Model model, ViewState viewState) {
        model.addListener(this, "balanceUpdate", "Price");
        this.balance = new SimpleStringProperty();
        this.companyName = new SimpleStringProperty();
        this.amount = new SimpleIntegerProperty();
        this.currentCompanySelected = new SimpleStringProperty();
        this.currentprice = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        list = FXCollections.observableArrayList();
        this.model = model;
        this.viewState = viewState;
    }

    public void reset() throws RemoteException {
        price.setValue(0);
        amount.setValue(0);
        balance.setValue(String.valueOf(model.getBalance(viewState.getUserName())));
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

    public StringProperty balanceProperty() {
        return balance;
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

    public IntegerProperty getAmount() {
        return amount;
    }

    /**
     * gets price
     *
     * @return price
     */

    public DoubleProperty getPrice() {
        return price;
    }


    public StringProperty getCurrentPrice() {
        return currentprice;
    }

    /**
     * current company selected bound to view controller
     *
     * @return selected company
     */

    public StringProperty currentCompanySelectedProperty() {
        return currentCompanySelected;
    }

    /**
     * Updates current price of selected company
     *
     * @param companyName of company
     */
    public void UpdateCurrentPrice(String companyName) {
        try {
            if (model.getComapnyByName(companyName) != null) {
                currentprice.setValue(model.getComapnyByName(companyName).getCurrentPrice().toString());
            }
        } catch (Exception e) {
            System.out.println("Select company");
        }
    }

    /**
     * Property change waiting for either balance change or Price change of company,
     * if there is it will update in view to newest information
     *
     * @param event
     */

    @Override
    public void propertyChange(ObserverEvent<String, Message> event) {
        Platform.runLater(() ->
        {
            try {
                if (!event.getPropertyName().equals("Price")) {
                    balance.setValue(event.getValue1());
                }
                if (event.getPropertyName().equals("Price")) {
                    if (currentCompanySelected != null) {
                        if (event.getValue1().equals(model.getComapnyByName(currentCompanySelected.get()).getSymbol())) {
                            currentprice.setValue(event.getValue2().getPriceObject().getPrice().toString());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        });
    }

    /**
     * On back depending if view state is true/false will turn back to company list or account view
     *
     * @return boolean
     */

    public boolean Back() {
        return viewState.isFromAccountView();
    }
}