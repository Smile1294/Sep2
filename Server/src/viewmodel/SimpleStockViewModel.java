package viewmodel;

import javafx.beans.property.*;
import model.Company;
import model.Orders;
import model.Stock;
import model.User;

import java.util.ArrayList;

import static java.lang.Double.NaN;

/**
 * SimpleCompanyViewModel is class for functionality of company
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
     * @param stock user stock
     * @param user which user
     * @param company which company
     * @param orders list of orders
     */

    public SimpleStockViewModel(Stock stock, User user, Company company, Orders orders) {

        username = new SimpleStringProperty(user.getUserName().getName());
        numberowned = new SimpleIntegerProperty(user.getStocks().getStock(stock).getAmount());
        currentValue = new SimpleDoubleProperty(company.getCurrentPrice() * user.getStocks().getStock(stock).getAmount());
        invested = new SimpleDoubleProperty(orders.getboughtPriceInStock(user, stock));
        name = new SimpleStringProperty(company.getName());
        value = new SimpleDoubleProperty(company.getCurrentPrice());

        if (((((company.getCurrentPrice() * user.getStocks().getStock(stock).getAmount()) / orders.getboughtPriceInStock(user, stock)) * 100) - 100) > 0) {
            percentage = new SimpleStringProperty("+" + Double.toString(((((company.getCurrentPrice() * user.getStocks().getStock(stock).getAmount()) / orders.getboughtPriceInStock(user, stock)) * 100) - 100)));
        } else {
            percentage = new SimpleStringProperty(Double.toString(((((company.getCurrentPrice() * user.getStocks().getStock(stock).getAmount()) / orders.getboughtPriceInStock(user, stock)) * 100) - 100)));
        }
        if (Double.isNaN(((((company.getCurrentPrice() * user.getStocks().getStock(stock).getAmount()) / orders.getboughtPriceInStock(user, stock)) * 100) - 100))) {
            percentage = new SimpleStringProperty(Double.toString(0.00));
        }
    }

    /**
     * gets the username
     * @return username
     */

    public StringProperty getUsername() {
        return username;
    }

    /**
     * gets number owned
     * @return number owned
     */

    public StringProperty getNumberowned() {
        return new SimpleStringProperty(Double.toString(numberowned.get()));
    }

    /**
     * gets percentage
     * @return percentage
     */

    public StringProperty getPercentage() {
        return percentage;
    }

    /**
     * gets current value
     * @return current value
     */

    public StringProperty getCurrentValue() {
        return new SimpleStringProperty(Double.toString(currentValue.get()));
    }

    /**
     * gets invested
     * @return invested
     */

    public StringProperty getinvested() {
        return new SimpleStringProperty(Double.toString(invested.get()));
    }

    /**
     * gets name
     * @return name
     */

    public StringProperty getName() {
        return name;
    }

    /**
     * gets value
     * @return value
     */

    public DoubleProperty getValue() {
        return value;
    }
}
