package model;

import java.util.ArrayList;

public class User implements Runnable {
    private StocksUserOwns stocks;
    private int balance;
    private String name;
    private Orders orders;

    public User(String name, int balance) {
        this.orders = new Orders();
        this.balance = balance;
        this.stocks = new StocksUserOwns(name);
        this.name = name;
    }

    public void Buy(Stock stock, int amount) {
        if (balance > stock.getPrice() * amount) {

            balance = balance - stock.getPrice() * amount;
            stock.setAmount(stock.getAmount() - amount);
            stocks.addStock(new Stock(stock.getName(), stock.getPrice(), amount));
        }
    }

    public StocksUserOwns getStocks() {
        return stocks;
    }


    public Stock Sell(Stock stock, int amount, int price) {

        return new Stock(stock.getName(), price, amount);
    }

    public void addOrdertoSell(Stock stock, int amount, int price) {
        try {
            stock.setAmount(stock.getAmount() - amount);
            orders.AddOrderToSell(new Stock(stock.getName(), price, amount));
            stocks.removeStock(stock);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void addOrderToBuy(Stock stock, int amount, int price) throws Exception {
        try {
            Stock stock1;
            if (orders.AddOrderToBuy(stock1 = new Stock(stock.getName(), price, amount)) != null) {
                stocks.addStock(stock1);
            } else {
                System.out.print(orders.toString());
                System.out.println("Order was added to list");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Orders getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "stocks=" + stocks +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void run() {


    }


}
