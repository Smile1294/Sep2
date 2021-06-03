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
    void getCompletedUserOwnedStockZero() {
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        orders.AddOrder(o1);
        assertEquals(orders.getUserOrders("") , orders.getCompletedOrders());

    }

    @Test
    void getCompletedUserOwnedStockOne() {
        Order o1 = new Order(false, BigDecimal.valueOf(1), 1, "user", Status.COMPLETED, "symbol");
        orders.AddOrder(o1);
        assertEquals(orders.getUserOrders("user") , orders.getCompletedOrders());

    }

    @Test
    void getCompletedUserOwnedStockMany()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(44), 33, "bob", Status.COMPLETED, "GOGL");
        orders.AddOrder(o1);
        Order o2 = new Order(false, BigDecimal.valueOf(4004), 3300, "bob", Status.COMPLETED, "APLE");
        orders.AddOrder(o2);
        assertEquals(orders.getUserOrders("bob") , orders.getCompletedOrders());
    }

    @Test
    void getCompletedUserOwnedStockBoundary() {
        // tested zero getCompletedUserOwnedStockZero()
    }

    @Test
    void getCompletedUserOwnedStockException() {
        // no exception to be thrown
    }



    @Test
    void toStringZero() {
        Orders o = new Orders();
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        Order o2 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        orders.AddOrder(o1);
        assertEquals(o1 , o1.toString());
    }



    @Test
    void getOrdersZero()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        orders.AddOrder(o1);
        ArrayList<Order> o = new ArrayList<>();
        o.add(o1);
        assertEquals(o , orders.getOrders());
    }

    @Test
    void getOrdersOne()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(1), 1, "bob", Status.COMPLETED, "symbol");
        orders.AddOrder(o1);
        ArrayList<Order> o = new ArrayList<>();
        o.add(o1);
        assertEquals(o , orders.getOrders());
    }

    @Test
    void getOrdersMany()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(9999), 9999, "bob999", Status.COMPLETED, "symbol999");
        Order o2 = new Order(true, BigDecimal.valueOf(345), 345, "user", Status.COMPLETED, "sym");
        Order o3 = new Order(true, BigDecimal.valueOf(20000), 20000, "bob999", Status.COMPLETED, "symbol");
        orders.AddOrder(o1);
        orders.AddOrder(o2);
        orders.AddOrder(o3);
        ArrayList<Order> o = new ArrayList<>();
        o.add(o1);
        o.add(o2);
        o.add(o3);
        assertEquals(o , orders.getOrders());
    }

    @Test
    void getOrdersBoundary() {
        // tested zero getOrdersZero()
    }

    @Test
    void getOrdersException() {
        // no exception to be thrown
    }











    @Test
    void getOrderbyIDZero()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        orders.AddOrder(o1);
        assertTrue(orders.getOrderbyId(o1));
    }

    @Test
    void getOrderbyIDOne()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(1), 1, "user", Status.COMPLETED, "symbol");
        orders.AddOrder(o1);
        assertTrue(orders.getOrderbyId(o1));
    }

    @Test
    void getOrderbyIDMany()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(30), 30, "user", Status.COMPLETED, "s");
        orders.AddOrder(o1);

        Order o2 = new Order(false, BigDecimal.valueOf(200), 200, "username", Status.OPEN, "symbol");
        orders.AddOrder(o2);

        assertTrue(orders.getOrderbyId(o1));
        assertTrue(orders.getOrderbyId(o2));

    }

    @Test
    void getOrderbyIDBoundary()
    {
        // tested in getOrderbyIDZero()
    }

    @Test
    void getOrderbyIDException()
    {
        // no exception to be thrown
    }









    @Test
    void getOrderbyIDZeroString()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        UUID id = UUID.randomUUID();
        orders.AddOrder(o1);
        o1.setOrderId(id);

        assertEquals(orders.getUserOrders("").get(0) , orders.getOrderbyID(id.toString()));
    }

    @Test
    void getOrderbyIDOneString()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(1), 1, "user", Status.COMPLETED, "symbol");
        UUID id = UUID.randomUUID();
        orders.AddOrder(o1);
        o1.setOrderId(id);

        assertEquals(orders.getUserOrders("user").get(0) , orders.getOrderbyID(id.toString()));
    }

    @Test
    void getOrderbyIDManyString()
    {
        Order o1 = new Order(true, BigDecimal.valueOf(100), 100, "random", Status.COMPLETED, "symbol");
        Order o2 = new Order(true, BigDecimal.valueOf(100), 100, "random", Status.COMPLETED, "symbol");
        Order o3 = new Order(true, BigDecimal.valueOf(100), 100, "random", Status.COMPLETED, "symbol");

        UUID id = UUID.randomUUID();
        orders.AddOrder(o1);
        orders.AddOrder(o2);
        orders.AddOrder(o3);
        o1.setOrderId(id);
        o2.setOrderId(id);
        o3.setOrderId(id);

        assertEquals(orders.getUserOrders("random").get(0) , orders.getOrderbyID(id.toString()));
    }

    @Test
    void getOrderbyIDBoundaryString()
    {
        // tested in getOrderbyIDZeroString()
    }

    @Test
    void getOrderbyIDExceptionString()
    {
        // no exception to be thrown
    }



    @Test
    void getOrderByUserZero()
    {
        User u = new User(new UserName("username") , new Password("Username123"));
        Order o1 = new Order(true, BigDecimal.valueOf(100), 100, u.getUserName().getName(), Status.COMPLETED, "symbol");
        orders.AddOrder(o1);
        Orders os = new Orders();
        os.AddOrder(o1);
        assertEquals(os , orders.getOrderByUser(u));
    }













    @Test
    void getBoughtPriceInStockZero()
    {
        User u = new User(new UserName("username") , new Password("Username123"));
        Stock s = new Stock("","");
        Order o1 = new Order(true, BigDecimal.valueOf(100), 100, u.getUserName().getName(), Status.COMPLETED, "symbol");
        orders.AddOrder(o1);
        assertEquals(0 , orders.getboughtPriceInStock(u,s));
    }

    @Test
    void getBoughtPriceInStockOne()
    {
        User u = new User(new UserName("username") , new Password("Username123"));
        Stock s = new Stock("eto","username");
        Order o1 = new Order(true, BigDecimal.valueOf(1), 1, u.getUserName().getName(), Status.COMPLETED, "eto");
        orders.AddOrder(o1);
        assertEquals(1 , orders.getboughtPriceInStock(u,s));
    }














}