package persistence;

import model.Companies;
import model.Company;

import java.sql.SQLException;
/**
 * CompaniesPersistence is interface for loading and saving companies
 */

public interface CompaniesPersistence {

    /**
     * loading companies list from a database
     * @return companies list
     */
    Companies load() throws SQLException;
    /**
     * updates database with newest informations about companies
     */
    void update(Company company)throws SQLException;
    /**
     * saving companies list into a database
     * @param company is company that is beeing saved
     */
    void save(Company company)throws SQLException;
    /**
     * removing company from database
     * @param company will be removed from database
     */
    void remove(Company company)throws SQLException;



}
