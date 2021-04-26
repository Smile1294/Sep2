package model;

import java.util.ArrayList;

public class Stock {
    private double price;
    private int amount;
    private Company company;

    public Stock(Company company, int amount) {
        this.company = company;
        this.amount = amount;
        this.price = company.getCurrentPrice();
    }

    public int getAmount() {
        return amount;
    }

    public Company getCompany() {
        return company;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Stock{" +
                "price=" + price +
                ", amount=" + amount +
                ", company=" + company +
                '}';
    }
}
