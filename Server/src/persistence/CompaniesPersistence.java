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
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    Companies load() throws SQLException;
    /**
     * Updates company in database
     * @param company company to be updated
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void update(Company company)throws SQLException;
    /**
     * saving companies list into a database
     * @param company is company that is beeing saved
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void save(Company company)throws SQLException;
    /**
     * removing company from database
     * @param company will be removed from database
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void remove(Company company)throws SQLException;



}
