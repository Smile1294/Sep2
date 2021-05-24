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




}