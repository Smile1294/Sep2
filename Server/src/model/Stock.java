package model;

/**
 * Stock class represents stock
 */

public class Stock {
    private double price;
    private int amount;
    private Company company;

    /**
     * Constructor which is setting up all the instance variables
     * @param company the company that has stock
     * @param amount a amount that of the stock that company holds
     */

    public Stock(Company company, int amount) {
        this.company = company;
        this.amount = amount;
        this.price = company.getCurrentPrice();
    }

    /**
     * getting the amount of stock
     * @return amount
     */

    public int getAmount() {
        return amount;
    }

    /**
     * getting the company
     * @return company
     */

    public Company getCompany() {
        return company;
    }

    /**
     * getting the price of the stock
     * @return price
     */

    public double getPrice() {
        return price;
    }

    /**
     * toString version of the stock
     * @return stock
     */

    @Override
    public String toString() {
        return "Stock{" +
                "price=" + price +
                ", amount=" + amount +
                ", company=" + company +
                '}';
    }
}
