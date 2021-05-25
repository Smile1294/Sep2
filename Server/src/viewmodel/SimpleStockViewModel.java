package viewmodel;

import javafx.beans.property.*;
import model.Company;
import model.Orders;
import model.Stock;
import model.User;

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
     * @param orders  list of orders
     */

    public SimpleStockViewModel(Stock stock, User user, Company company, Orders orders) {

        username = new SimpleStringProperty(user.getUserName().getName());
        numberowned = new SimpleIntegerProperty(stock.getAmount());
        currentValue = new SimpleStringProperty(String.valueOf(company.getCurrentPrice() * stock.getAmount()));
        invested = new SimpleDoubleProperty(stock.getPrice());
        name = new SimpleStringProperty(company.getName());
        value = new SimpleDoubleProperty(company.getCurrentPrice());
        symbol = new SimpleStringProperty(company.getSymbol());

        if (((((company.getCurrentPrice() * stock.getAmount()) / orders.getboughtPriceInStock(user, stock)) * 100) - 100) > 0) {
            percentage = new SimpleStringProperty("+" + (((((company.getCurrentPrice() * stock.getAmount()) / orders.getboughtPriceInStock(user, stock)) * 100) - 100)));
        } else {
            percentage = new SimpleStringProperty(Double.toString(((((company.getCurrentPrice() * stock.getAmount()) / orders.getboughtPriceInStock(user, stock)) * 100) - 100)));
        }
        if (Double.isNaN(((((company.getCurrentPrice() * stock.getAmount()) / orders.getboughtPriceInStock(user, stock)) * 100) - 100))) {
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
     * get symbol of company
     *
     * @return symbol
     */
    public StringProperty getSymbol() {
        return symbol;
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
        return currentValue;
    }

    /**
     * gets invested
     *
     * @return invested
     */

    public StringProperty getinvested() {
        return new SimpleStringProperty(String.valueOf(invested.get()));
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
