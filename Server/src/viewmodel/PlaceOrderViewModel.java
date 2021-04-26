package viewmodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.ModelManger;
import model.Stock;

import java.io.IOException;

public class PlaceOrderViewModel {
    private Model model;
    private ObservableList<String> list;
    private SimpleStringProperty price;
    private SimpleStringProperty amount;
    private SimpleStringProperty balance;
    private ViewState viewState;


    public PlaceOrderViewModel(Model model, ViewState viewState){
        this.balance = new SimpleStringProperty();
        this.amount = new SimpleStringProperty();
        this.price = new SimpleStringProperty();
        list = FXCollections.observableArrayList();
        this.model = model;
        this.viewState = viewState;
    }

    public ObservableList getStockChoice() {
//        for (int i = 0; i < model.getStocks().getSize(); i++) {
//            list.add(model.getStocks().getStock(i).getName());
//        }
//        return list;
        return null;
    }

    public SimpleStringProperty balanceProperty() {
        return balance;
    }

    public void buy(String stock) throws Exception {
//        for (int i = 0; i < model.getStocks().getSize(); i++) {
//            if (stock.equals(model.getStocks().getStock(i).getName())) {
//                model.PlaceOrdertoBuy(model.getStocks().getStock(i), Integer.parseInt(getAmount().get()), Integer.parseInt(getPrice().get()));
//            }
//        }
    }

    public void sell(String stock) {
//        for (int i = 0;i<model.getStocks().getSize();i++)
//        {
//            if(stock.equals(model.getStocks().getStock(i).getName()))
//            {
//                model.PlaceOrdertoSell(model.getStocks().getStock(i),Integer.parseInt(getAmount().get()),Integer.parseInt(getPrice().get()));
//            }
//        }
    }

    public SimpleStringProperty getAmount() {
        return amount;
    }

    public SimpleStringProperty getPrice() {
        return price;
    }
}
