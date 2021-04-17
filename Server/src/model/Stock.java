package model;

import java.util.ArrayList;

public class Stock {
    private String name;
    private int price;
    private User user;
    private int amount;
    private double invested;

    public Stock(String name, int price, int amount) {
        this.invested = 0;
        this.amount = amount;
        this.name = name;
        this.price = price;
    }

    public double invested() {
        return invested;
    }

    public void addtoInvested(double add) {
        this.invested = invested + add;
    }

    public void setInvested(double invested) {
        this.invested = invested;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
