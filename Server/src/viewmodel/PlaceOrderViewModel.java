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
import java.util.UUID;

/**
 * PlaceOrderViewModel is class for functionality of login view
 */

public class PlaceOrderViewModel implements LocalListener<String, Message> {
    private Model model;
    private SimpleStringProperty balance;
    private SimpleStringProperty currentprice;
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
        model.addListener(this, "balanceUpdate", "Price");
        this.balance = new SimpleStringProperty();
        this.companyName = new SimpleStringProperty();
        this.currentprice = new SimpleStringProperty();
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
     * On back depending if view state is true/false will turn back to company list or acount view
     * @return boolean
     */
    public boolean Back() {
        return viewState.isFromAccountView();
    }

    /**
     * gets companies that user wants
     *
     * @return list of companies
     */

    public ObservableList<String> getStockChoice() {
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

    public SimpleStringProperty balanceProperty() {
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

    public SimpleStringProperty getCurrentPrice(String nameofcompany) {
        try {
            return new SimpleStringProperty(model.getComapnyByName(nameofcompany).getCurrentPrice().toString());
        } catch (Exception e) {
            System.out.println("No company selected");
        }
        return currentprice;
    }

    /**
     * Property change waiting for either balance change or Price change of company,
     * if there is it will update in view
     * @param event
     */
    @Override
    public void propertyChange(ObserverEvent<String, Message> event) {
        Platform.runLater(() ->
        {
            try {
                balance.setValue(event.getValue1());
            } catch (Exception e) {
                System.out.println(e);
            }
            if (event.getPropertyName().equals("Price")) {
                currentprice.setValue(event.getValue2().getPriceObject().toString());
            }

        });
    }

}