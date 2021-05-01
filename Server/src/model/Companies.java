package model;

import java.util.ArrayList;

public class Companies {
    public ArrayList<Company> companies;
    public Companies()
    {
        this.companies = new ArrayList<>();
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public Company getCompany(Company company)
    {
        for(Company companies2:companies)
        {
            if(companies2 == company)
            {
                return companies2;
            }
        }
        return null;
    }

    public Company getCompany(String symbol) {
        for(Company c:companies) {
            if(c.getSymbol().equals(symbol)) {
                return c;
            }
        }
        return null;
    }

    public void addCompany(Company company)
    {
        companies.add(company);
    }
    public void removeCompany(Company company)
    {
        companies.remove(company);
    }

}
