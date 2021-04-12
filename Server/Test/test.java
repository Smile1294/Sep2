import model.Orders;
import model.Stock;
import model.Stocks;
import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class test {
    private Stock stock;
    private Stocks stocks;
    private User user;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {

        System.out.println("Begin");
        stock = new Stock("Apple", 15, 10);
        stocks = new Stocks("Market");
        stocks.addStock(stock);
        user = new User("David", "1234");
        user.addOrderToBuy(stock, 1, 5);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.out.println("Ended");
    }

    @Test
    void isStockInList() {
        assertFalse(user.getOrders() == null);
    }
}