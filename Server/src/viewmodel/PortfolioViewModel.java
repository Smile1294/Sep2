package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.io.IOException;
import java.security.KeyStore;

public class PortfolioViewModel {
    private Model model;
    private StringProperty name;
    private DoubleProperty total;
    private DoubleProperty investedValue;
    private ObservableList<SimpleStockViewModel> simpleStockViewModels;
    private ViewState viewState;


    public PortfolioViewModel(Model model, ViewState viewState) throws IOException {
        this.viewState = viewState;
        this.model = model;
        this.investedValue = new SimpleDoubleProperty();
        this.name = new SimpleStringProperty();
        this.total = new SimpleDoubleProperty();
        simpleStockViewModels = FXCollections.observableArrayList();
        loadUserStock();

    }

    public void clear() {
        this.name.setValue(viewState.getUserName().getName());
        this.total.setValue(Math.round(model.getUser(viewState.getUserName().getName()).getBalance()));
        this.investedValue = null;
        simpleStockViewModels.removeAll(getAll());
        getPriceTotal();
        loadUserStock();
    }

    public ObservableList<SimpleStockViewModel> getAll() {
        return simpleStockViewModels;
    }

    public DoubleProperty getInvestedValue() {
        return investedValue;
    }

    private void loadUserStock() {
        try {
            for (Stock s : model.LoaduserStocks(viewState.getUserName().getName())) {
                simpleStockViewModels.add(new SimpleStockViewModel(s, model.getUser(viewState.getUserName().getName()), model.getCompanyBySymbol(s.getSymbol()), model.getPortfolioOrders(model.getUser(viewState.getUserName().getName()))));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public DoubleProperty getPriceTotal() {
        return new SimpleDoubleProperty(model.getPriceTotal(viewState.getUserName().getName()));
    }

    public StringProperty getName() {
        return name;
    }

    public DoubleProperty getTotal() {

        return total;

    }
}

