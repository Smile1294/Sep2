import model.Companies;
import model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompaniesTest {

    Companies c;

    @BeforeEach
    void setUp()
    {
        c = new Companies();
    }


    private ArrayList<Company> toStringCompaniesZero()
    {
        c.AddCompany(new Company("" , ""));
        return c.getCompanies();
    }

    @Test
    void getCompaniesZero() {
        assertEquals(toStringCompaniesZero() , c.getCompanies());
    }



    private ArrayList<Company> toStringCompaniesOne()
    {
        c.AddCompany(new Company("Apple" , "a"));
        return c.getCompanies();
    }

    @Test
    void getCompaniesOne()
    {
        assertEquals(toStringCompaniesOne(), c.getCompanies());
    }


    private ArrayList<Company> toStringCompaniesMany()
    {
        c.AddCompany(new Company("Apple" , "a"));
        c.AddCompany(new Company("Google" , "g"));
        return c.getCompanies();
    }

    @Test
    void getCompaniesMany()
    {
        assertEquals(toStringCompaniesMany(), c.getCompanies());
    }

    @Test
    void getCompaniesBoundary()
    {
        // zero and one already tested
    }

    @Test
    void getCompaniesException()
    {
        // cant be tested since no exception is thrown
    }






    @Test
    void getCompanyZero()
    {
        Company company = new Company("" , "");
        c.AddCompany(company);
        assertEquals(company, c.getCompany(company));
    }

    @Test
    void getCompanyOne()
    {
        Company company = new Company("Apple" , "AAPL");
        c.AddCompany(company);
        assertEquals(company, c.getCompany(company));
    }

    @Test
    void getCompanyMany()
    {
        Company company = new Company("Apple" , "AAPL");
        c.AddCompany(company);

        Company company1 = new Company("Google" , "GOGL");
        c.AddCompany(company1);

        assertEquals(company1, c.getCompany(company1));
        assertEquals(company, c.getCompany(company));
    }

    @Test
    void getCompanyBoundary()
    {
        // tested zero getCompanyZero()
    }

    @Test
    void getCompanyException()
    {
        // no exception to be thrown
    }











    @Test
    void getCompanyBySymbolZero()
    {
        Company company = new Company("" , "");
        c.AddCompany(company);
        assertEquals(company, c.getCompanyBySymbol(""));
    }

    @Test
    void getCompanyBySymbolOne()
    {
        Company company = new Company("name" , "symbol");
        c.AddCompany(company);
        assertEquals(company, c.getCompanyBySymbol("symbol"));
    }

    @Test
    void getCompanyBySymbolMany()
    {

        Company company = new Company("name" , "symbol");
        c.AddCompany(company);

        Company company1 = new Company("symbol" , "name");
        c.AddCompany(company1);

        assertEquals(company1, c.getCompanyBySymbol("name"));
        assertEquals(company, c.getCompanyBySymbol("symbol"));

    }

    @Test
    void getCompanyBySymbolBoundary()
    {
        // tested zero getCompanyZero()
    }

    @Test
    void getCompanyBySymbolException()
    {
        // no exception to be thrown
    }






    @Test
    void getCompanyByNameZero()
    {
        Company company = new Company("" , "");
        c.AddCompany(company);
        assertEquals(company, c.getCompanyByName(""));
    }

    @Test
    void getCompanyByNameOne()
    {
        Company company = new Company("name" , "symbol");
        c.AddCompany(company);
        assertEquals(company, c.getCompanyByName("name"));
    }

    @Test
    void getCompanyByNameMany()
    {

        Company company = new Company("name" , "symbol");
        c.AddCompany(company);

        Company company1 = new Company("symbol" , "name");
        c.AddCompany(company1);

        assertEquals(company, c.getCompanyByName("name"));
        assertEquals(company1, c.getCompanyByName("symbol"));

    }

    @Test
    void getCompanyByNameBoundary()
    {
        // tested zero getCompanyByNameZero()
    }

    @Test
    void getCompanyByNameException()
    {
        // no exception to be thrown
    }







    @Test
    void removeCompanyZero()
    {
        Company company = new Company("" , "");
        c.AddCompany(company);
        c.RemoveCompany(company);
        assertNull(c.getCompanyByName(""));
    }

    @Test
    void removeCompanyOne()
    {
        Company company = new Company("name" , "symbol");
        c.AddCompany(company);
        c.AddCompany(company);
        c.RemoveCompany(company);
        assertEquals(company, c.getCompanyByName("name"));
    }

    @Test
    void removeCompanyMany()
    {
        Company company = new Company("name" , "symbol");
        c.AddCompany(company);
        c.AddCompany(company);
        c.RemoveCompany(company);

        Company company1 = new Company("symbol" , "name");
        c.AddCompany(company1);
        c.AddCompany(company1);
        c.RemoveCompany(company1);

        assertEquals(company, c.getCompanyByName("name"));
        assertEquals(company1, c.getCompanyByName("symbol"));
    }

    @Test
    void removeCompanyBoundary()
    {
        // tested zero removeCompanyZero()
    }

    @Test
    void removeCompanyException()
    {
        // no exception to be thrown
    }






}