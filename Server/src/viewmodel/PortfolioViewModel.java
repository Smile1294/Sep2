package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.KeyStore;

/**
 * PortfolioViewModel is class for functionality of portfolio view
 */

public class PortfolioViewModel implements LocalListener<String, Message> {
    private Model model;
    private StringProperty name;
    private DoubleProperty total;
    private DoubleProperty investedValue;
    private ObservableList<SimpleStockViewModel> simpleStockViewModels;
    private ViewState viewState;

    /**
     * Constructor that is initialising all the instance variables
     *
     * @param model     model for functionality
     * @param viewState viewState state of the account
     * @throws IOException
     */

    public PortfolioViewModel(Model model, ViewState viewState) throws IOException {
        this.viewState = viewState;
        this.model = model;
        this.investedValue = new SimpleDoubleProperty();
        this.name = new SimpleStringProperty();
        this.total = new SimpleDoubleProperty();
        simpleStockViewModels = FXCollections.observableArrayList();
        model.addListener(this, "Price");
    }

    /**
     * clears the information and sets it to default
     */

    public void clear() {
        this.name.setValue(viewState.getUserName().getName());
        this.investedValue = null;
        simpleStockViewModels.removeAll(getAll());
        total.setValue(Math.round(model.getPriceTotal(viewState.getUserName().getName()) * 100.0) / 100.0);
        loadUserStock();
    }

    /**
     * gets the stock model view
     *
     * @return stock model view
     */

    public ObservableList<SimpleStockViewModel> getAll() {
        return simpleStockViewModels;
    }

    /**
     * gets invested
     *
     * @return invested
     */

    public DoubleProperty getInvestedValue() {
        return investedValue;
    }

    /**
     * loads from user account
     */

    private void loadUserStock() {
        try {
            for (Stock s : model.LoaduserStocks(viewState.getUserName().getName())) {
                simpleStockViewModels.add(new SimpleStockViewModel(s, model.getUser(viewState.getUserName().getName()), model.getCompanyBySymbol(s.getSymbol()), model.getPortfolioOrders(model.getUser(viewState.getUserName().getName()))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * gets total price
     *
     * @return price
     */

    public DoubleProperty getPriceTotal() {
        return total;
    }

    /**
     * gets name
     *
     * @return name
     */

    public StringProperty getName() {
        return name;
    }

    /**
     * gets total
     *
     * @return total
     */


    /**
     * Updates values in portofilio, if company price changes
     *
     * @param event
     */
    @Override
    public void propertyChange(ObserverEvent<String, Message> event) {
        for (SimpleStockViewModel s : simpleStockViewModels) {
            if (s.getSymbol().get().equals(event.getValue1())) {
                Platform.runLater(() -> {
                    simpleStockViewModels.removeAll(getAll());
                    loadUserStock();
                    total.setValue(Math.round(model.getPriceTotal(viewState.getUserName().getName()) * 100.0) / 100.0);
                });
            }
        }
    }
}

