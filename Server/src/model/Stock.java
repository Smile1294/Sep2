package model;

import mediator.Symbol;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Stock {
    private String username;
    private String symbol;
    private int amount;

    public Stock(String symbol,String username) {
        this.symbol = symbol;
        this.username = username;
        this.amount = 0;
    }
    public Stock(String symbol,String username,int amount) {
        this.symbol = symbol;
        this.username = username;
        this.amount = amount;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "username='" + username + '\'' +
                ", symbol='" + symbol + '\'' +
                ", amount=" + amount +
                '}';
    }
}
