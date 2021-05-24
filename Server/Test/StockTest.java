import model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    Stock s;


    @BeforeEach
    void setUp()
    {
        s = new Stock("" , "");
        s = new Stock("" , "" , 0);
    }

    @Test
    void getAmountZero()
    {
        assertEquals(0 , s.getAmount());
    }

    @Test
    void getAmountOne()
    {
        s.setAmount(1);
        assertEquals(1 , s.getAmount());
    }

    @Test
    void getAmountMany()
    {
        s.setAmount(5000);
        assertEquals(5000 , s.getAmount());


        s.setAmount(235);
        assertEquals(235 , s.getAmount());
    }

    @Test
    void getAmountBoundary()
    {
        // zero and one already tested
        s.setAmount(-1);
        assertEquals(-1 , s.getAmount());
    }

    @Test
    void getAmountException()
    {

    }


    @Test
    void setAmount()
    {
        // tested in getAmount methods
    }

    @Test
    void getUsernameZero()
    {
        assertEquals("" , s.getUsername());
    }

    @Test
    void getUsernameOne()
    {
        s.setUsername("a");
        assertEquals("a" , s.getUsername());
    }

    @Test
    void getUsernameMany()
    {
        s.setUsername("yes");
        assertEquals("yes" , s.getUsername());

        s.setUsername("no");
        assertEquals("no" , s.getUsername());
    }

    @Test
    void getUsernameBoundary()
    {
        // zero and one already tested
    }

    @Test
    void getUsernameException()
    {
        // cant be tested since no exception is thrown
    }

    @Test
    void setUsername()
    {
        // tested in getUsername methods
    }



    @Test
    void getSymbolZero()
    {
        assertEquals("" , s.getSymbol());
    }

    @Test
    void getSymbolOne()
    {
        s.setSymbol("a");
        assertEquals("a" , s.getSymbol());
    }

    @Test
    void getSymbolMany()
    {
        s.setSymbol("bob");
        assertEquals("bob" , s.getSymbol());

        s.setSymbol("obo12345");
        assertEquals("obo12345" , s.getSymbol());
    }

    @Test
    void getSymbolBoundary()
    {
        // zero and one already tested
    }

    @Test
    void getSymbolException()
    {
        // cant be tested since no exception is thrown
    }

    @Test
    void setSymbol()
    {
        // tested in getSymbol methods
    }


    @Test
    void toStringZero()
    {
        assertEquals("Stock{username='', symbol='', amount=0}" , s.toString());
    }

    @Test
    void toStringOne()
    {
        s = new Stock("random" ,"random",  69);
        assertEquals("Stock{username='random', symbol='random', amount=69}" , s.toString());
    }

    @Test
    void toStringMany()
    {
        s = new Stock("random123" ,"random456",  420);
        assertEquals("Stock{username='random123', symbol='random456', amount=69}" , s.toString());


        s = new Stock("not random" ,"not random",  69);
        assertEquals("Stock{username='not random', symbol='not random', amount=225883}" , s.toString());
    }

    @Test
    void toStringBoundary()
    {
        // zero and one already tested
    }

    @Test
    void toStringException()
    {
        // cant be tested since no exception is thrown
    }

}