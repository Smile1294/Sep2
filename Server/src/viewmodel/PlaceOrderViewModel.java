package viewmodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.math.BigDecimal;

/**
 * PlaceOrderViewModel is class for functionality of login view
 */

public class PlaceOrderViewModel {
    private Model model;
    private ObservableList<String> list;
    private SimpleIntegerProperty price;
    private SimpleIntegerProperty amount;
    private SimpleIntegerProperty balance;
    private ViewState viewState;

    /**
     * Constructor that is initialising all the instance variables
     *
     * @param model     model for functionality
     * @param viewState viewState state of the account
     */

    public PlaceOrderViewModel(Model model, ViewState viewState) {
        this.balance = new SimpleIntegerProperty();
        this.amount = new SimpleIntegerProperty();
        this.price = new SimpleIntegerProperty();
        list = FXCollections.observableArrayList();
        this.model = model;
        this.viewState = viewState;
    }

    /**
     * gets companies that user wants
     *
     * @return list of companies
     */

    public ObservableList getStockChoice() {
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

    public SimpleIntegerProperty balanceProperty() {
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

    //make price get big decimal,amount integer,user
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
}