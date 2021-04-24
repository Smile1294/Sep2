package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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


    public PortfolioViewModel(Model model,ViewState viewState) throws IOException {
        this.viewState = viewState;
        this.model = model;
        this.investedValue = new SimpleDoubleProperty();
        this.name = new SimpleStringProperty();
        this.total = new SimpleDoubleProperty();
        simpleStockViewModels = FXCollections.observableArrayList();
        loadUserStock();
        total = getPriceTotal();

    }

    public ObservableList<SimpleStockViewModel> getAll() {
        return simpleStockViewModels;
    }

    public DoubleProperty getInvestedValue() {
        return investedValue;
    }

    private void loadUserStock() {
//        try {
//            for (Stock s : model.getAllStocks()) {
//                simpleStockViewModels.add(new SimpleStockViewModel(s, model.getUser()));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }


    public DoubleProperty getPriceTotal()
    {
//        double d = 0.0;
//        try {
//            for (Stock s : model.getAllStocks()) {
//                if (model.getUser().getSpecific(s.getName()).getAmount() > 0) {
//                    d = d + s.getPrice() * model.getUser().getSpecific(s.getName()).getAmount();
//                    System.out.println(d);
//                }
//            }
//            return new SimpleDoubleProperty(d);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return new SimpleDoubleProperty(d);
        return null;
    }

    public StringProperty getName() {
        return name;
    }

    public DoubleProperty getTotal() {

        return total;

    }
}

