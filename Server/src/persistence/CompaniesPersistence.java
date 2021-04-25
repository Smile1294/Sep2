package persistence;

import model.Companies;


public interface CompaniesPersistence {
    void save(Companies companiesList, String filename);
    Companies load(String fileName);
}
