package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.KeyStore;

/**
 * PortfolioViewModel is class for functionality of portfolio view
 */

public class PortfolioViewModel {
    private Model model;
    private StringProperty name;
    private DoubleProperty total;
    private DoubleProperty investedValue;
    private ObservableList<SimpleStockViewModel> simpleStockViewModels;
    private ViewState viewState;

    /**
     * Constructor that is initialising all the instance variables
     * @param model model for functionality
     * @param viewState viewState state of the account
     * @throws IOException
     */

    public PortfolioViewModel(Model model, ViewState viewState) {
        this.viewState = viewState;
        this.model = model;
        this.investedValue = new SimpleDoubleProperty();
        this.name = new SimpleStringProperty();
        this.total = new SimpleDoubleProperty();
        simpleStockViewModels = FXCollections.observableArrayList();
    }

    /**
     * clears the information and sets it to default
     */

    public void clear() {
        this.name.setValue(viewState.getUserName().getName());
        try {
            this.total.setValue(Math.round(model.getUser(viewState.getUserName().getName()).getBalance()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.investedValue = null;
        simpleStockViewModels.removeAll(getAll());
        getPriceTotal();
        loadUserStock();
    }

    /**
     * gets the stock model view
     * @return stock model view
     */

    public ObservableList<SimpleStockViewModel> getAll() {
        return simpleStockViewModels;
    }

    /**
     * gets invested
     * @return invested
     */

    public DoubleProperty getInvestedValue() {
        return investedValue;
    }

    /**
     * loads from user account stocks
     */

    private void loadUserStock() {
        try {
            for (Stock s : model.LoaduserStocks(viewState.getUserName().getName())) {
                simpleStockViewModels.add(new SimpleStockViewModel(s, model.getUser(viewState.getUserName().getName()), model.getCompanyBySymbol(s.getSymbol())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * gets total price
     * @return price
     */


    public DoubleProperty getPriceTotal()  {
        try {
            return new SimpleDoubleProperty(model.getPriceTotal(viewState.getUserName().getName()));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return new SimpleDoubleProperty(0.0);
    }
    /**
     * gets name
     * @return name
     */

    public StringProperty getName() {
        return name;
    }

    /**
     * gets total
     * @return total
     */

    public DoubleProperty getTotal() {
        return total;
    }
}

