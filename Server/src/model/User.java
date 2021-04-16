package model;


import java.util.ArrayList;

public class User {
    private StocksUserOwns stocks;
    private int balance;
    private String name;
    private String password;
    private Orders orders;
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        orders = new Orders();
        balance = 0;
        stocks = new StocksUserOwns(name);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void Buy(Stock stock, int amount) {
        if (balance > stock.getPrice() * amount) {
            balance = balance - stock.getPrice() * amount;
            stock.setAmount(stock.getAmount() - amount);
            Stock es = new Stock(stock.getName(), stock.getPrice(), amount);
            stocks.addStock(es);
            stocks.getSpecificStock(es).addtoInvested(amount*stock.getPrice());
        }
    }

    public Stock getSpecific(String name) {
        for(Stock s: stocks.getAllStocks())
        {
            if(s.getName().equals(name))
            {
                return s;
            }
        }
        return null;
    }



    public ArrayList<Stock> getStocks() {
        return stocks.getAllStocks();
    }

    public double getInvested(Stock stock) {
        for(Stock s: stocks.getAllStocks())
        {
            if(s.getName().equals(stock.getName()))
            {
                return s.invested();
            }
        }
        return 0;
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


    public void addOrderToBuy(Stock stock, int amount, int price) {
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
}
