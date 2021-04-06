package model;

public class ModelManger implements Model {
    private Orders orders;
    private User user;
    private Stocks stocks;

    public ModelManger() {
        orders = new Orders();
        user = new User("bob", 1500);
        stocks = new Stocks("Market");
        stocks.addStock(new Stock("Apple", 5, 5));
        stocks.addStock(new Stock("Microsoft", 5, 5));
        stocks.addStock(new Stock("Kebab", 5, 5));
    }

    public Stocks getStocks() {
        return stocks;
    }

    public Orders getOrders() {
        return orders;
    }

    public void PlaceOrdertoSell(Stock stock, int amount, int price) {
        user.addOrdertoSell(stock, amount, price);

    }

    public void PlaceOrdertoBuy(Stock stock, int amount, int price) throws Exception {
        user.addOrderToBuy(stock, amount, price);
    }
}
