import model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    Company c;

    @BeforeEach
    void setUp() {
        c = new Company("", "");
    }

    @Test
    void getOneName() {
        c = new Company("a", "");
        assertEquals("a", c.getName());
    }

    @Test
    void getZeroNames() {
        assertEquals("", c.getName());
    }

    @Test
    void getManyNames() {
        c = new Company("abc ,abc ,abc", "");
        assertEquals("abc ,abc ,abc", c.getName());

        c = new Company(null, "");
        assertNull(null, c.getName());
    }

    @Test
    void getBoundaryNames() {
        // zero already tested
    }

    @Test
    void getExceptionNames() {
        // no exception in the method
    }


    @Test
    void getCurrentPriceZero() {
        c.setCurrentPrice(0);
        assertEquals(0, c.getCurrentPrice());
    }

    @Test
    void getCurrentPriceOne() {
        c.setCurrentPrice(1);
        assertEquals(1, c.getCurrentPrice());
    }

    @Test
    void getCurrentPriceMany() {
        c.setCurrentPrice(10);
        assertEquals(10, c.getCurrentPrice());

        c.setCurrentPrice(3);
        assertEquals(3, c.getCurrentPrice());
    }

    @Test
    void getCurrentPriceBoundary() {
        // tested zero and one lower left boundary
    }

    @Test
    void getCurrentPriceException() {
        // cant be tested since no exception is thrown
    }


    @Test
    void setCurrentPriceZero() {
        // tested in getCurrentPriceZero
    }

    @Test
    void setCurrentPriceOne() {
        // getCurrentPriceOne
    }

    @Test
    void setCurrentPriceMany() {
        // tested in getCurrentPriceMany
    }

    @Test
    void setCurrentPriceBoundary() {

        // tested zero and one lower left boundary

        assertThrows(IllegalArgumentException.class ,() -> c.setCurrentPrice(-1));


    }

    @Test
    void setCurrentPriceException() {
        // cant be tested since no exception is thrown

        assertThrows(IllegalArgumentException.class ,() -> c.setCurrentPrice(-45));

        assertThrows(IllegalArgumentException.class ,() -> c.setCurrentPrice(-140));

    }


    @Test
    void getSymbolZero() {
        assertEquals("", c.getSymbol());
    }

    @Test
    void getSymbolOne() {
        c = new Company("", "w");
        assertEquals("w", c.getSymbol());
    }

    @Test
    void getSymbolMany() {
        c = new Company("", "data");
        assertEquals("data", c.getSymbol());

        c = new Company("", "data");
        assertEquals("data", c.getSymbol());

    }

    @Test
    void getSymbolBoundary() {
        // tested zero and one lower left boundary
    }

    @Test
    void getSymbolException()
    {
        // cant be tested since no exception is thrown
    }



    @Test
    void toStringZero()
    {
        c.setCurrentPrice(0.0);
        assertEquals("Company{name='', prices=0, Symbol=''}" , c.toString());
    }

    @Test
    void toStringOne()
    {
        c = new Company("bob" , "bob");
        c.setCurrentPrice(1);
        assertEquals("Company{name='bob', prices=1, Symbol='bob'}" , c.toString());
    }

    @Test
    void toStringMany()
    {
        c = new Company("name123456789" , "symbol123456789");
        c.setCurrentPrice(15);
        assertEquals("Company{name='name123456789', prices=15, Symbol='symbol123456789'}" , c.toString());


        c = new Company("eto" , "aaa");
        c.setCurrentPrice(5);
        assertEquals("Company{name='eto', prices=5, Symbol='aaa'}" , c.toString());
    }

    @Test
    void toStringBoundary()
    {
        // tested zero
    }

    @Test
    void toStringException()
    {
        // cant be tested since no exception is thrown
    }

}