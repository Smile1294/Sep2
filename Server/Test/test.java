import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class test {
    private Stock stock;
    private Stocks stocks;
    private User user;
    private Model model;
    private UserList userList;
    private Company company;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        this.company = new Company("Tesla Inc.", "TSLA");
        this.userList = new UserList();
        this.model = new ModelManger();
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

    void isStockInList() throws Exception {
        assertTrue(company.getPrices() != null);
        assertTrue(userList.userExist("kim", "123"));
        assertFalse(user.getOrders() == null);


    }
}