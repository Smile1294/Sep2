package persistence;

import model.Companies;

/**
 * CompaniesPersistence is interface for loading and saving companies
 */

public interface CompaniesPersistence {

    /**
     * saving companies list into a file
     * @param companiesList list of companies that is being stored
     * @param filename name of a file that is information stored on
     */

    void save(Companies companiesList, String filename);

    /**
     * loading companies list from a file
     * @param fileName from file to load
     * @return companies list
     */

    Companies load(String fileName);
}
