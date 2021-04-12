package model;

public class Test {
    public static void main(String[] args) {
        User user = new User("David","1234");
        Stocks stocks = new Stocks("Shop");
        stocks.addStock(new Stock("Apple",5,5));
        user.Buy(stocks.getStock(0),1);
        System.out.println(stocks.toString());
        user.addOrdertoSell(user.getStocks().getStock(0),1,3);
        System.out.println(user.getOrders());
        user.addOrderToBuy(stocks.getStock(0),1,4);
        System.out.println(user.getStocks());
        System.out.println(user.getOrders());
    }
}
