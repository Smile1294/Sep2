package persistence;

import model.Companies;
import model.Company;

import java.sql.SQLException;

public interface CompaniesPersistence {
    Companies load() throws SQLException;
    void update(Company company)throws SQLException;
    void save(Company company)throws SQLException;
    void remove(Company company)throws SQLException;
}
