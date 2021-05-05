package model;

import java.util.ArrayList;

public class Companies {
    public ArrayList<Company> companies;

    public Companies() {
        this.companies = new ArrayList<>();
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public Company getCompany(Company company) {
        for (Company companies2 : companies) {
            if (companies2 == company) {
                return companies2;
            }
        }
        return null;
    }

    public Company getCompanyBySymbol(String symbol) {
        for (Company d : companies) {
            if (d.getSymbol().equals(symbol)) {
                return d;
            }
        }
        return null;
    }

    public Company getCompanyByName(String name) {
        for (Company c : companies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }


    public void AddCompany(Company company) {
        companies.add(company);
    }

    public void RemoveCompany(Company company) {
        companies.remove(company);
    }

}
