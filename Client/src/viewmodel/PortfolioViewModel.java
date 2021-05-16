package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Stock;

import java.io.IOException;

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

    public PortfolioViewModel(Model model, ViewState viewState) throws IOException {
        this.viewState = viewState;
        this.model = model;
        this.investedValue = new SimpleDoubleProperty();
        this.name = new SimpleStringProperty();
        this.total = new SimpleDoubleProperty();
        simpleStockViewModels = FXCollections.observableArrayList();
        loadUserStock();

    }

    /**
     * clears the information and sets it to default
     */

    public void clear() {
        this.name.setValue(viewState.getUserName().getName());
        this.total.setValue(Math.round(model.getUser(viewState.getUserName().getName()).getBalance()));
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
     * loads from user account
     */

    private void loadUserStock() {
        try {
            for (Stock s : model.LoaduserStocks(viewState.getUserName().getName())) {
                simpleStockViewModels.add(new SimpleStockViewModel(s, model.getUser(viewState.getUserName().getName()),
                        model.getCompanyBySymbol(s.getSymbol())/*,
                        model.getPortfolioOrders(model.getUser(viewState.getUserName().getName()))*/));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    /**
     * gets total price
     * @return price
     */

    public DoubleProperty getPriceTotal() {
//        create a private doubleproperty that will be updated in in clear method
//        this doubleproperty is then returned in this method
//        return new SimpleDoubleProperty(model.getPriceTotal(viewState.getUserName().getName()));

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
