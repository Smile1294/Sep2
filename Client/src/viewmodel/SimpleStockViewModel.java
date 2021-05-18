package viewmodel;

import javafx.beans.property.*;
import model.*;

import java.util.ArrayList;

/**
 * SimpleCompanyViewModel is class for functionality of portfolio
 */

public class SimpleStockViewModel {
    private StringProperty name;
    private DoubleProperty value;
    private DoubleProperty invested;
    private DoubleProperty currentValue;
    private StringProperty percentage;
    private IntegerProperty numberowned;
    private StringProperty username;

    /**
     * Constructor that is initialising all the instance variables
     *
     * @param stock   user stock
     * @param user    which user
     * @param company which company
     */

    public SimpleStockViewModel(Stock stock, User user, Company company) {

        username = new SimpleStringProperty(user.getUserName().getName());
        numberowned = new SimpleIntegerProperty(stock.getAmount());
        currentValue = new SimpleDoubleProperty(company.getCurrentPrice() * stock.getAmount());
        invested = new SimpleDoubleProperty(stock.getPrice());
        name = new SimpleStringProperty(company.getName());
        value = new SimpleDoubleProperty(company.getCurrentPrice());

        if (((((company.getCurrentPrice() * stock.getAmount()) / stock.getPrice()) * 100) - 100) > 0) {
            percentage = new SimpleStringProperty("+" + Double.toString(((((company.getCurrentPrice() * stock.getAmount()) / stock.getPrice()) * 100) - 100)));
        } else {
            percentage = new SimpleStringProperty(Double.toString(((((company.getCurrentPrice() * stock.getAmount()) / stock.getPrice()) * 100) - 100)));
        }
        if (Double.isNaN(((((company.getCurrentPrice() * stock.getAmount()) / stock.getPrice()) * 100) - 100))) {
            percentage = new SimpleStringProperty(Double.toString(0.00));
        }
    }

    /**
     * gets the username
     *
     * @return username
     */

    public StringProperty getUsername() {
        return username;
    }

    /**
     * gets number owned
     *
     * @return number owned
     */

    public StringProperty getNumberowned() {
        return new SimpleStringProperty(Double.toString(numberowned.get()));
    }

    /**
     * gets percentage
     *
     * @return percentage
     */

    public StringProperty getPercentage() {
        return percentage;
    }

    /**
     * gets current value
     *
     * @return current value
     */

    public StringProperty getCurrentValue() {
        return new SimpleStringProperty(Double.toString(currentValue.get()));
    }

    /**
     * gets invested
     *
     * @return invested
     */

    public StringProperty getinvested() {
        return new SimpleStringProperty(Double.toString(invested.get()));
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
     * gets value
     *
     * @return value
     */

    public DoubleProperty getValue() {
        return value;
    }
}
