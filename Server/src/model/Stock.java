package model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Stock class represents stock
 */


public class Stock implements Serializable {
    private String username;
    private String symbol;
    private int amount;
    private double price;

    /**
     * Constructor initialising the instance variables
     * @param symbol symbol of the company
     * @param username username of the user
     */

    public Stock(String symbol,String username) {
        this.symbol = symbol;
        this.username = username;
        this.amount = 0;
        this.price = 0.0;
    }

    /**
     * Constructor initialising the instance variables
     * @param symbol symbol of the company
     * @param username username of the user
     * @param amount amount of the stock
     */

    public Stock(String symbol,String username,int amount) {
        this.symbol = symbol;
        this.username = username;
        this.amount = amount;
        this.price = 0.0;
    }
    /**
     * setting price of stock
     @throws IllegalArgumentException if the user puts negative value
     */
    public void setPrice(double price) {
        if (price < 0.0) throw new IllegalArgumentException();
        this.price = price;
    }
    /**
     * getting price of stock
     * @return getPriceObject
     */
    public double getPrice() {
        return price;
    }

    /**
     * getting the amount of stock
     * @return amount
     */

    public int getAmount() {
        return amount;
    }

    /**
     * sets the amount of stock
     * @param amount amount that is being set
     * @throws IllegalArgumentException if the user sets amount to negative value
     */

    public void setAmount(int amount) {

        if(amount < 0) throw new IllegalArgumentException();

        this.amount = amount;
    }

    /**
     * gets username
     * @return username
     */

    public String getUsername() {
        return username;
    }

    /**
     * sets the username
     * @param username username that is being set
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets the symbol
     * @return symbol
     */

    public String getSymbol() {
        return symbol;
    }

    /**
     * sets the symbol
     * @param symbol symbol that is being set
     */

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * toString version of the stock
     * @return stock
     */

    @Override
    public String toString() {
        return "Stock{" +
                "username='" + username + '\'' +
                ", symbol='" + symbol + '\'' +
                ", amount=" + amount +
                '}';
    }
}
