import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {
    Orders orders;


    @BeforeEach
    void setUp() {
        orders = new Orders();
    }


    @Test
    void addOrdersZero() {
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        orders.AddOrder(o1);
        assertEquals(o1, orders.getUserOrders("").get(0));
    }

    @Test
    void addOrdersOne() {
        Order o1 = new Order(true, BigDecimal.valueOf(1), 1, "bob", Status.COMPLETED, "b");
        orders.AddOrder(o1);
        assertEquals(o1, orders.getUserOrders("bob").get(0));
    }

    @Test
    void addOrdersMany() {
        Order o1 = new Order(true, BigDecimal.valueOf(22), 99, "bob", Status.COMPLETED, "p");
        orders.AddOrder(o1);
        Order o2 = new Order(true, BigDecimal.valueOf(55), 100, "bob", Status.COMPLETED, "s");
        orders.AddOrder(o2);
        assertEquals(o2, orders.getUserOrders("bob").get(1));
    }

    @Test
    void addOrdersBoundary() {
        // tested zero in addOrdersZero()
    }

    @Test
    void addOrdersException() {
        // no exceptions to be thrown
    }


    @Test
    void closeOrderZero() {
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.OPEN, "");
        orders.AddOrder(o1);
        orders.closeOrder(o1);
        assertEquals(Status.CLOSED, o1.getStatus());
    }


    @Test
    void closeOrderOne() {
        Order o1 = new Order(true, BigDecimal.valueOf(22), 99, "bob", Status.OPEN, "p");
        orders.AddOrder(o1);
        orders.closeOrder(o1);
        assertEquals(Status.CLOSED, o1.getStatus());
    }

    @Test
    void closeOrderMany() {
        Order o1 = new Order(false, BigDecimal.valueOf(55), 420, "bob12345", Status.OPEN, "");
        orders.AddOrder(o1);
        orders.closeOrder(o1);
        assertEquals(Status.CLOSED, o1.getStatus());

        Order o2 = new Order(true, BigDecimal.valueOf(300), 1000, "et", Status.COMPLETED, "GOGL");
        orders.AddOrder(o2);
        orders.closeOrder(o2);
        assertEquals(Status.CLOSED, o2.getStatus());
    }

    @Test
    void closeOrderBoundary() {
        // tested zero in closeOrderZero()
    }

    @Test
    void closeOrderException() {
        // no exceptions to be thrown
    }


    @Test
    void getForSaleZero() {
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.OPEN, "");
        ArrayList<Order> o = new ArrayList<>();
        orders.AddOrder(o1);
        o.add(o1);
        assertEquals(o, orders.getForSale());
    }

    @Test
    void getForSaleOne() {
        Order o1 = new Order(true, BigDecimal.valueOf(1), 1, "user", Status.OPEN, "symbol");
        ArrayList<Order> o = new ArrayList<>();
        orders.AddOrder(o1);
        o.add(o1);
        assertEquals(o, orders.getForSale());
    }

    @Test
    void getForSaleMany() {
        Order o1 = new Order(true, BigDecimal.valueOf(9999.9), 20, "bob", Status.OPEN, "symbol");
        Order o2 = new Order(true, BigDecimal.valueOf(111111), 11, "username", Status.OPEN, "GOGL");
        ArrayList<Order> o = new ArrayList<>();
        orders.AddOrder(o1);
        orders.AddOrder(o2);
        o.add(o1);
        o.add(o2);
        assertEquals(o, orders.getForSale());
    }

    @Test
    void getForSaleBoundary() {
        // tested zero in getForSaleZero()
    }

    @Test
    void getForSaleException() {
        // no exception to be thrown
    }


    @Test
    void getToBuyZero() {
        // not working yet
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.OPEN, "");
        ArrayList<Order> o = new ArrayList<>();
        orders.AddOrder(o1);
        o.add(o1);
        assertEquals(orders.getUserOrders(""), orders.getToBuy());
    }


    @Test
    void getUserOrdersZero() {
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.OPEN, "");
        ArrayList<Order> o = new ArrayList<>();
        orders.AddOrder(o1);
        o.add(o1);
        assertEquals(o, orders.getUserOrders(""));
    }

    @Test
    void getUserOrdersOne() {
        Order o1 = new Order(true, BigDecimal.valueOf(1), 1, "user", Status.OPEN, "symbol");
        ArrayList<Order> o = new ArrayList<>();
        orders.AddOrder(o1);
        o.add(o1);
        assertEquals(o, orders.getUserOrders("user"));
    }

    @Test
    void getUserOrdersMany() {
        Order o1 = new Order(true, BigDecimal.valueOf(9999.9), 20, "bob", Status.OPEN, "symbol");
        Order o2 = new Order(true, BigDecimal.valueOf(111111), 11, "bob", Status.OPEN, "GOGL");
        ArrayList<Order> o = new ArrayList<>();
        orders.AddOrder(o1);
        orders.AddOrder(o2);
        o.add(o1);
        o.add(o2);
        assertEquals(o, orders.getUserOrders("bob"));
    }

    @Test
    void getUserOrdersBoundary() {
        // tested zero in getUserOrdersZero()
    }

    @Test
    void getUserOrdersException() {
        // no exception to be thrown
    }


    /////////////////////////////////////////////////


    @Test
    void getOrderByUserZero() {
        /*
            cant be tested since username and password are throwing exceptions if their character requirements arent met

        User u = new User(new UserName(""), new Password(""));
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, u.toString(), Status.OPEN, "");
        orders.AddOrder(o1);
        assertEquals(orders , orders.getOrderByUser(u));

         */
    }

    @Test
    void getOrderByUserOne() {
        User u = new User(new UserName("bob"), new Password("Bob123"));
        Orders o = new Orders();
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "bob", Status.OPEN, "");
        orders.AddOrder(o1);
        o.AddOrder(o1);
        assertEquals(o, orders.getOrderByUser(u));
    }


    @Test
    void getCompletedUserOwnedStockZero() {
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        orders.AddOrder(o1);


    }

    @Test
    void getCompletedUserOwnedStockOne() {
        Order o1 = new Order(false, BigDecimal.valueOf(1), 1, "user", Status.COMPLETED, "symbol");
        orders.AddOrder(o1);

    }

    @Test
    void getCompletedUserOwnedStockMany() {
        Order o1 = new Order(false, BigDecimal.valueOf(1), 1, "user", Status.COMPLETED, "symbol");
        orders.AddOrder(o1);
        orders.AddOrder(o1);
        orders.AddOrder(o1);



    }

    @Test
    void getCompletedUserOwnedStockBoundary() {
        // tested zero getCompletedUserOwnedStockZero()
    }

    @Test
    void getCompletedUserOwnedStockException() {
        // no exception to be thrown
    }


//////////////////////////////////////////////


    @Test
    void getBoughtPriceZero() {
        /*
        // cant be tested since username and password are throwing exceptions if their character requirements arent met

        User u = new User(new UserName(""), new Password(""));
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        orders.AddOrder(o1);
        assertEquals(0, orders.getboughtPrice(u));
         */
    }

    @Test
    void getBoughtPriceOne() {
        User u = new User(new UserName("bob"), new Password("Bob123"));
        Order o1 = new Order(false, BigDecimal.valueOf(1), 1, u.toString(), Status.OPEN, "et");
        orders.AddOrder(o1);
        orders.AddOrder(o1);
        o1.setAmount(1);

    }


    /////////////////////////////////////////

    @Test
    void getBoughtPriceInStockZero() {
        /*

        // cant be tested since username and password are throwing exceptions if their character requirements arent met

        User u = new User(new UserName("bob"), new Password("Bob123"));
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        orders.AddOrder(o1);

         */
    }

    @Test
    void getBoughtPriceInStockOne() {
        User u = new User(new UserName("bob"), new Password("Bob123"));
        Stock s = new Stock("symbol", "et", 1);
        Order o1 = new Order(true, BigDecimal.valueOf(1), 1, "et", Status.COMPLETED, "symbol");
        orders.AddOrder(o1);
        assertEquals(1, orders.getboughtPriceInStock(u, s));
    }

    ///////////////////////////////

    @Test
    void tpStringZero() {
        Orders o = new Orders();
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        Order o2 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        o.AddOrder(o2);
        orders.AddOrder(o1);


    }


}