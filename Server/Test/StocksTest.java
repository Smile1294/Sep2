import model.Stock;
import model.Stocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StocksTest {

    Stocks stocks;

    @BeforeEach
    void setUp() {
        stocks = new Stocks();
    }

    @Test
    void getAllStocksZero()
    {
        Stock s = new Stock("" , "");
        stocks.addStock(s);
        ArrayList<Stock> list = new ArrayList<>();
        list.add(s);
        assertEquals(list , stocks.getAllStocks());
    }

    @Test
    void getAllStocksOne()
    {
        Stock s1 = new Stock("eto" , "username");
        stocks.addStock(s1);
        ArrayList<Stock> list = new ArrayList<>();
        list.add(s1);
        assertEquals(list , stocks.getAllStocks());
    }

    @Test
    void getAllStocksMany()
    {
        Stock s = new Stock("eto" , "username");
        stocks.addStock(s);
        Stock s1 = new Stock("eto" , "username");
        stocks.addStock(s1);
        ArrayList<Stock> list = new ArrayList<>();
        list.add(s);
        list.add(s1);
        assertEquals(list , stocks.getAllStocks());
    }

    @Test
    void getAllStocksBoundary()
    {
        // tested zero in getAllStocksZero()
    }

    @Test
    void getAllStocksException()
    {
        // no exception thrown
    }









    @Test
    void getSizeZero()
    {
        assertEquals(0 , stocks.getSize());
    }

    @Test
    void getSizeOne()
    {
        stocks.addStock(new Stock("random" , "name"));
        assertEquals(1 , stocks.getSize());
    }

    @Test
    void getSizeMany()
    {
        stocks.addStock(new Stock("G" , "user"));
        stocks.addStock(new Stock("A" , "name"));
        stocks.addStock(new Stock("S" , "username"));
        assertEquals(3 , stocks.getSize());
    }

    @Test
    void getSizeBoundary()
    {
        // zero tested getSizeZero()
    }

    @Test
    void getSizeException()
    {
        // no exception thrown
    }








    @Test
    void getStockZero()
    {
        Stock s = new Stock("" , "");
        stocks.addStock(s);
        assertEquals(s, stocks.getStock(0));
    }

    @Test
    void getStockOne()
    {
        Stock s = new Stock("G" , "username");
        stocks.addStock(s);
        assertEquals(s, stocks.getStock(0));
    }

    @Test
    void getStockMany()
    {
        Stock s = new Stock("eto" , "username");
        stocks.addStock(s);
        Stock s1 = new Stock("eto" , "username");
        stocks.addStock(s1);
        assertEquals(s, stocks.getStock(0));
        assertEquals(s1 , stocks.getStock(1));
    }

    @Test
    void getStockBoundary()
    {
        // zero tested in getStockZero()

    }

    @Test
    void getStockException()
    {
        // no exception thrown
    }









    @Test
    void removeStockZero()
    {
        Stock s1 = new Stock("" , "");
        stocks.addStock(s1);
        stocks.removeStock(s1);
        assertNull(stocks.getStock(s1));
    }

    @Test
    void removeStockOne()
    {
        Stock s1 = new Stock("xd" , "user");
        stocks.addStock(s1);
        stocks.addStock(s1);
        stocks.removeStock(s1);
        assertEquals(s1  , stocks.getStock(0));
    }

    @Test
    void removeStockMany()
    {
        Stock s1 = new Stock("xd" , "user");
        stocks.addStock(s1);
        stocks.addStock(s1);
        stocks.addStock(s1);
        stocks.addStock(s1);
        stocks.addStock(s1);
        stocks.addStock(s1);
        stocks.removeStock(s1);
        stocks.removeStock(s1);
        assertEquals(s1  , stocks.getStock(3));
    }

    @Test
    void removeStockBoundary()
    {
        // tested zero in removeStockZero()
    }

    @Test
    void removeStockException()
    {
        // no exception thrown
    }







    @Test
    void getStockBySymbolZero()
    {
        Stock s1 = new Stock("" , "");
        stocks.addStock(s1);
        assertEquals(s1 , stocks.getStockBySymbol(""));
    }

    @Test
    void getStockBySymbolOne()
    {
        Stock s1 = new Stock("s" , "");
        stocks.addStock(s1);
        assertEquals(s1 , stocks.getStockBySymbol("s"));
    }

    @Test
    void getStockBySymbolMany()
    {
        Stock s1 = new Stock("s" , "name");
        stocks.addStock(s1);
        Stock s2 = new Stock("lol" , "user");
        stocks.addStock(s2);
        assertEquals(s2 , stocks.getStockBySymbol("lol"));
    }

    @Test
    void getStockBySymbolBoundary()
    {
        // zero tested
    }

    @Test
    void getStockBySymbolException()
    {
        // no exception thrown
    }








    @Test
    void toStringZero()
    {
        assertEquals("Stocks{stocks=[]}" , stocks.toString() );
    }

    @Test
    void toStringOne()
    {
        Stock s1 = new Stock("symbol" , "username");
        stocks.addStock(s1);
        assertEquals("Stocks{stocks=[Stock{username='username', symbol='symbol', amount=0}]}" , stocks.toString());
    }

    @Test
    void toStringMany()
    {
        Stock s1 = new Stock("sym" , "user");
        stocks.addStock(s1);
        Stock s2 = new Stock("bol/*-" , "name12345@..");
        stocks.addStock(s2);
        assertEquals("Stocks{stocks=[Stock{username='user', symbol='sym', amount=0}, Stock{username='name12345@..', symbol='bol/*-', amount=0}]}" , stocks.toString());
    }

    @Test
    void toStringBoundary()
    {
        // tested zero in toStringZero()
    }

    @Test
    void toStringException()
    {
        // no exception thrown
    }






    @Test
    void getStockZeroByStock()
    {
        Stock s1 = new Stock("" , "");
        stocks.addStock(s1);
        assertEquals(s1 , stocks.getStock(s1));
    }

    @Test
    void getStockOneByStock()
    {
        Stock s1 = new Stock("symbol" , "username");
        stocks.addStock(s1);
        assertEquals(s1 , stocks.getStock(s1));
    }

    @Test
    void getStockManyByStock()
    {
        Stock s1 = new Stock("GOGL" , "bob");
        stocks.addStock(s1);

        Stock s2 = new Stock("APLE" , "martin");
        stocks.addStock(s2);

        assertEquals(s1 , stocks.getStock(s1));
        assertEquals(s2 , stocks.getStock(s2));

    }

    @Test
    void getStockBoundaryByStock()
    {
        // zero tested in getStockZeroByStock()
    }

    @Test
    void getStockExceptionByStock()
    {
        // no exception to be thrown
    }



}

