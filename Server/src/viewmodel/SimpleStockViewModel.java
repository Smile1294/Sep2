package viewmodel;

import javafx.beans.property.*;
import model.Stock;
import model.User;

public class SimpleStockViewModel {
    private StringProperty name;
    private DoubleProperty value;
    private DoubleProperty invested;
    private DoubleProperty currentValue;
    private StringProperty percentage;
    private IntegerProperty numberowned;
    private StringProperty username;


    public SimpleStockViewModel(Stock stock, User user) {

        username = new SimpleStringProperty(user.getName());
        numberowned = new SimpleIntegerProperty(user.getSpecific(stock.getName()).getAmount());
        currentValue = new SimpleDoubleProperty(stock.getPrice()*user.getSpecific(stock.getName()).getAmount());
        invested = new SimpleDoubleProperty(user.getInvested(stock));
        name = new SimpleStringProperty(stock.getName());
        value = new SimpleDoubleProperty(stock.getPrice());
        percentage = new SimpleStringProperty(Double.toString((stock.getPrice()*user.getSpecific(stock.getName()).getAmount()/user.getInvested(stock))*100));
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
        return new SimpleStringProperty(Double.toString(currentValue.get()));
    }

    public StringProperty getinvested() {
        return new SimpleStringProperty(Double.toString(invested.get()));
    }

    public StringProperty getName() {
        return name;
    }

    public DoubleProperty getValue() {
        return value;
    }
}
