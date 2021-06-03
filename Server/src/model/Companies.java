package model;

import java.util.ArrayList;

/**
 * constructor which is initialising arraylist of companies
 */

public class Companies
{
    private ArrayList<Company> companies;
    /**
     * constructor which is initialising arraylist of companies
     */

    public Companies() {
        this.companies = new ArrayList<>();
    }

    /**
     * getting the companies
     * @return companies
     */

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    /**
     * getting the company
     * @param company company that is wanted
     * @return company
     */

    public Company getCompany(Company company) {
        for (Company companies2 : companies) {
            if (companies2 == company) {
                return companies2;
            }
        }
        return null;
    }

    /**
     * getting the company by symbol
     * @param symbol symbol of the company
     * @return company
     */

    public Company getCompanyBySymbol(String symbol) {
        for (Company d : companies) {
            if (d.getSymbol().equals(symbol)) {
                return d;
            }
        }
        return null;
    }

    /**
     * getting the company by name
     * @param name name of the company
     * @return company
     */
    public Company getCompanyByName(String name) {
        for (Company c : companies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * adds a company to the list
     * @param company company that is being added
     */


    public void AddCompany(Company company) {
        companies.add(company);
    }

    /**
     * removes a company from list
     * @param company company that is being removed
     */


    public void RemoveCompany(Company company) {
        companies.remove(company);
    }

}
