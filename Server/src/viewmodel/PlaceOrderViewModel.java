package viewmodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.Symbol;
import model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class PlaceOrderViewModel {
    private Model model;
    private ObservableList<String> list;
    private SimpleIntegerProperty price;
    private SimpleIntegerProperty amount;
    private SimpleIntegerProperty balance;
    private ViewState viewState;


    public PlaceOrderViewModel(Model model, ViewState viewState){
        this.balance = new SimpleIntegerProperty();
        this.amount = new SimpleIntegerProperty();
        this.price = new SimpleIntegerProperty();
        list = FXCollections.observableArrayList();
        this.model = model;
        this.viewState = viewState;
    }

    public ObservableList getStockChoice() {
        for (int i = 0; i < model.getAllCompanies().size();i++) {
        list.add(model.getAllCompanies().get(i).getName());
        }
        return list;
    }

    public SimpleIntegerProperty balanceProperty() {
        return balance;
    }

    public void buy(String nameofcompany) throws Exception {
        model.AddOrder(new Order(false, BigDecimal.valueOf(price.get()), amount.get(),viewState.getUserName().getName(), Status.OPEN,model.getComapnyByName(nameofcompany).getSymbol()));
    }
    //make price get big decimal,amount integer,user
    public void sell(String nameofcompany) {
        model.AddOrder(new Order(true, BigDecimal.valueOf(price.get()), amount.get(),viewState.getUserName().getName(), Status.OPEN,model.getComapnyByName(nameofcompany).getSymbol()));
    }

    public SimpleIntegerProperty getAmount() {
        return amount;
    }

    public SimpleIntegerProperty getPrice() {
        return price;
    }
}