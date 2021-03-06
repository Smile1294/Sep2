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
    private StringProperty currentValue;
    private StringProperty percentage;
    private IntegerProperty numberowned;
    private StringProperty username;
    private StringProperty symbol;


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
        currentValue = new SimpleStringProperty(String.valueOf(Math.round(company.getCurrentPrice() * stock.getAmount() * 100) / 100.0));
        invested = new SimpleDoubleProperty(stock.getPrice());
        name = new SimpleStringProperty(company.getName());
        value = new SimpleDoubleProperty(company.getCurrentPrice());
        symbol = new SimpleStringProperty(company.getSymbol());


        if (((((company.getCurrentPrice() * stock.getAmount()) / stock.getPrice()) * 100) - 100) > 0) {
            double d = (((((company.getCurrentPrice() * stock.getAmount()) / stock.getPrice()) * 100) - 100));
            d = Math.round(d * 100) / 100.0;
            percentage = new SimpleStringProperty("+" + d);
        } else {
            double d = (((((company.getCurrentPrice() * stock.getAmount()) / stock.getPrice()) * 100) - 100));
            d = Math.round(d * 100) / 100.0;
            percentage = new SimpleStringProperty(String.valueOf(d));
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
     * get symbol of company
     *
     * @return symbol
     */
    public StringProperty getSymbol() {
        return symbol;
    }

    /**
     * gets current value
     *
     * @return current value
     */

    public StringProperty getCurrentValue() {
        return currentValue;
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

}
