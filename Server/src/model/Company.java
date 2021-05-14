package model;

import java.math.BigDecimal;

/**
 * Company class is representing company that holds a stock
 */

public class Company {
    private String name;
    private BigDecimal currentPrice;
    private String Symbol;

    /**
     * Constructor that is setting all the instance variables
     * @param name name of the stock
     * @param symbol symbol of the company
     */

    public Company(String name,String symbol)
    {
        this.name = name;
        this.Symbol = symbol;
        this.currentPrice = new BigDecimal("0.0");
    }

    /**
     * getting the name of the stock
     * @return stock
     */

    public String getName() {
        return name;
    }

    /**
     * getting the current price
     * @return current price
     */


    public Double getCurrentPrice() {
        return currentPrice.doubleValue();
    }

    /**
     * setting the price
     * @param currentPrice setting the price for the stock
     */

    public void setCurrentPrice(double currentPrice){
        this.currentPrice = new BigDecimal(currentPrice);
    }

    /**
     * getting the symbol of the company
     * @return symbol
     */

    public String getSymbol() {
        return Symbol;
    }

    /**
     * toString version of the company
     * @return company
     */

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", prices=" + currentPrice +
                ", Symbol='" + Symbol + '\'' +
                '}';
    }
}
