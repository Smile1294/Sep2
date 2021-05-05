package viewmodel;

import javafx.beans.property.*;
import model.Company;
import model.Orders;
import model.Stock;
import model.User;

import java.util.ArrayList;

public class SimpleStockViewModel {
    private StringProperty name;
    private DoubleProperty value;
    private DoubleProperty invested;
    private DoubleProperty currentValue;
    private StringProperty percentage;
    private IntegerProperty numberowned;
    private StringProperty username;


    public SimpleStockViewModel(Stock stock, User user, Company company,Orders orders) {

        username = new SimpleStringProperty(user.getUserName().getName());
        numberowned = new SimpleIntegerProperty(user.getStocks().getStock(stock).getAmount());
        currentValue = new SimpleDoubleProperty(company.getCurrentPrice() * user.getStocks().getStock(stock).getAmount());
        invested = new SimpleDoubleProperty(orders.getboughtPriceInStock(user,stock));
        name = new SimpleStringProperty(company.getName());
        value = new SimpleDoubleProperty(company.getCurrentPrice());
        percentage = new SimpleStringProperty(Double.toString(((((company.getCurrentPrice()*user.getStocks().getStock(stock).getAmount())/orders.getboughtPriceInStock(user,stock))*100)-100)));
    }


    public StringProperty getUsername() {
        return username;
    }


    public StringProperty getNumberowned() {
        return new SimpleStringProperty(Double.toString(numberowned.get()));
    }

    public StringProperty getPercentage() {
        return percentage;
    }

    public StringProperty getCurrentValue() {
        return new SimpleStringProperty(Double.toString(Math.round(currentValue.get())));
    }

    public StringProperty getinvested() {
        return new SimpleStringProperty(Double.toString(Math.round(invested.get())));
    }

    public StringProperty getName() {
        return name;
    }

    public DoubleProperty getValue() {
        return value;
    }
}
