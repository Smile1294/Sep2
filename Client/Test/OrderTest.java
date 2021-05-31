import model.Order;
import model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;


    @BeforeEach
    void setUp() {
        order = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "");

    }






    @Test
    void getZeroSymbol() {
        assertEquals("", order.getSymbol());
    }

    @Test
    void getOneSymbol() {
        order = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "a");
        assertEquals("a", order.getSymbol());

    }

    @Test
    void getManySymbols() {

        order = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "anna");
        assertEquals("anna", order.getSymbol());

        order = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "bob");
        assertEquals("bob", order.getSymbol());
    }







    @Test
    void getInitialAmountZero() {
        order = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "");
        assertEquals(0, order.getInitialAmount());
    }

    @Test
    void getInitialAmountOne() {
        order = new Order(true, BigDecimal.valueOf(0), 1, "a", Status.COMPLETED, "");
        assertEquals(1, order.getInitialAmount());
    }

    @Test
    void getInitialAmountMany() {
        order = new Order(true, BigDecimal.valueOf(0), 3, "a", Status.COMPLETED, "");
        assertEquals(3, order.getInitialAmount());
    }

    ////////////

    @Test
    void setInitialAmountBoundary() {

        assertThrows(IllegalArgumentException.class, () -> order.setAmount(-1));
    }

    @Test
    void setInitialAmountException()
    {
        assertThrows(IllegalArgumentException.class, () -> order.setAmount(-353));
        assertThrows(IllegalArgumentException.class, () -> order.setAmount(-2));
        assertThrows(IllegalArgumentException.class, () -> order.setAmount(-19));


    }


    @Test
    void isSellZero() {
        order = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        assertTrue(order.isSell());
    }

    @Test
    void isSellOne() {
        order = new Order(true, BigDecimal.valueOf(0), 1, "bob", Status.COMPLETED, "");
        assertTrue(order.isSell());
    }

    @Test
    void isSellMany()
    {
        order = new Order(true, BigDecimal.valueOf(333), 19, "bob", Status.OPEN, "A");
        assertTrue(order.isSell());

        order = new Order(false, BigDecimal.valueOf(100), 50, "tony", Status.OPEN, "G");
        assertFalse(order.isSell());
    }

    @Test
    void isSellBoundary()
    {
        // tested in isSellZero()
    }

    @Test
    void isSellException()
    {
        // no exception thrown
    }








    @Test
    void getAskingPriceZero()
    {
        order.setAskingPrice(BigDecimal.valueOf(0));
        assertEquals(0 , order.getAskingPrice());
    }

    @Test
    void getAskingPriceOne()
    {
        order = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "a");
        order.setAskingPrice(BigDecimal.valueOf(1));
        assertEquals(1 , order.getAskingPrice());
    }

    @Test
    void getAskingPriceMany()
    {
        order = new Order(true, BigDecimal.valueOf(32), 1, "a", Status.COMPLETED, "a");
        order.setAskingPrice(BigDecimal.valueOf(32));
        assertEquals(32 , order.getAskingPrice());

        order = new Order(true, BigDecimal.valueOf(450), 1, "a", Status.COMPLETED, "a");
        order.setAskingPrice(BigDecimal.valueOf(450));
        assertEquals(450 , order.getAskingPrice());

        order = new Order(true, BigDecimal.valueOf(111), 1, "a", Status.COMPLETED, "a");
        order.setAskingPrice(BigDecimal.valueOf(111));
        assertEquals(111 , order.getAskingPrice());
    }


    @Test
    void getAskingPriceBoundary()
    {

        assertThrows(IllegalArgumentException.class , () -> order.setAskingPrice(BigDecimal.valueOf(-1)));


        // zero already tested in getAskingPriceZero()

    }

    @Test
    void getAskingPriceException()
    {
        assertThrows(IllegalArgumentException.class , () -> order.setAskingPrice(BigDecimal.valueOf(-132)));
        assertThrows(IllegalArgumentException.class , () -> order.setAskingPrice(BigDecimal.valueOf(-2)));
        assertThrows(IllegalArgumentException.class , () -> order.setAskingPrice(BigDecimal.valueOf(-1500)));
    }




    @Test
    void getAmountZero()
    {
        assertEquals(0 , order.getAmount());
    }

    @Test
    void getAmountOne()
    {
        order.setAmount(1);
        assertEquals(1 , order.getAmount());
    }

    @Test
    void getAmountMany()
    {
        order.setAmount(20);
        assertEquals(20 , order.getAmount());
    }

    @Test
    void getAmountBoundary()
    {
        order.setAmount(-1);
        assertEquals(0 , order.getAmount());

        // already tested in getAmountMany()
    }

    @Test
    void getAmountException()
    {
        // no exception thrown
    }






    @Test
    void getStatusZero()
    {
        order.setStatus(Status.COMPLETED);
        assertEquals(Status.COMPLETED , order.getStatus());
    }

    @Test
    void getStatusOne()
    {
        order.setStatus(Status.OPEN);
        assertEquals(Status.OPEN , order.getStatus());
    }

    @Test
    void getStatusMany()
    {
        order.setStatus(Status.CLOSED);
        assertEquals(Status.CLOSED , order.getStatus());
    }





    @Test
    void getUserZero()
    {
        order = new Order(true, BigDecimal.valueOf(0), 0, "", Status.COMPLETED, "");
        assertEquals("" , order.getUser());
    }

    @Test
    void getUserOne()
    {
        order = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "");
        assertEquals("a" ,  order.getUser());
    }

    @Test
    void getUserMany()
    {
        order = new Order(true, BigDecimal.valueOf(0), 0, "bob", Status.COMPLETED, "");
        assertEquals("bob" , order.getUser());


        order = new Order(true, BigDecimal.valueOf(0), 0, "user", Status.COMPLETED, "");
        assertEquals("user" , order.getUser());

    }

    @Test
    void getUserBoundary()
    {
        order = new Order(true, BigDecimal.valueOf(0), 0, null, Status.COMPLETED, "");
        assertNull(order.getUser());

        // tested zero in getUserZero()
    }

    @Test
    void getUserException()
    {
        // no exception thrown
    }

    @Test
    void setAmount()
    {
        // tested in getAmount methods
    }

    @Test
    void setStatus()
    {
        // tested in getStatus methods
    }

    @Test
    void close()
    {
        // tested in getStatusMany()
    }

    @Test
    void complete()
    {
        // tested in getStatusZero()
    }

    @Test
    void getOrderIdZero()
    {
        UUID id = UUID.randomUUID();
        order.setOrderId(id);
        assertEquals(id.toString() , order.getOrderId());
    }

    @Test
    void getOrderIdOne()
    {
        UUID id = UUID.randomUUID();
        order.setOrderId(id);
        assertEquals(id.toString() , order.getOrderId());

    }

    @Test
    void getOrderIdMany()
    {
        Order o = new Order(true , BigDecimal.valueOf(1) , 1 ,"" , Status.OPEN , "");
        UUID id = UUID.randomUUID();
        order.setOrderId(id);

        UUID id1 = UUID.randomUUID();
        o.setOrderId(id1);

        assertEquals(id1.toString() , o.getOrderId());
        assertEquals(id.toString() , order.getOrderId());
    }

    @Test
    void getOrderIdBoundary()
    {
        // tested zero in getOrderIdZero()
    }

    @Test
    void getOrderIdException()
    {
        // no exception to be thrown
    }




    @Test
    void equalsZero()
    {
        Order o = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "a");
        order = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "a");

    }



    @Test
    void toStringZero()
    {
        order = new Order(true, BigDecimal.valueOf(0), 0, "a", Status.COMPLETED, "a");
        UUID id1 = UUID.randomUUID();
        order.setOrderId(id1);
        String aString= order.toString();
        String result = UUID.nameUUIDFromBytes(aString.getBytes()).toString();
        assertEquals("Order{, sell=true, askingPrice=0, amount=0, status=COMPLETED, user=a, orderId=b8d3802a-f3e5-4a4a-8c40-b7d34820f1e0}", result);
    }


}