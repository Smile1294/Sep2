package model;

import java.math.BigDecimal;


public class Company {
    private String name;
    private BigDecimal currentPrice;
    private String Symbol;
    public Company(String name,String symbol)
    {
        this.name = name;
        this.Symbol = symbol;
        this.currentPrice = new BigDecimal("0.0");
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice.doubleValue();
    }

    public void setCurrentPrice(double currentPrice){
        this.currentPrice = new BigDecimal(currentPrice);
    }

    public String getSymbol() {
        return Symbol;
    }


    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", prices=" + currentPrice +
                ", Symbol='" + Symbol + '\'' +
                '}';
    }
}
