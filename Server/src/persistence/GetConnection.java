package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Class GetConnection connects to database with
 * URL,USER,PASSWORD
 * that are needed to access the database
 */
public class GetConnection {
    private static final String URL = "jdbc:postgresql://tai.db.elephantsql.com:5432/swjaurgb?currentSchema=sep2";
    private static final String USER = "swjaurgb";
    private static final String PASSWORD = "3CqmA8u3ha9nknGYO1D7FqdQ072gixMo";

    /**
     * gets connection with database
     * @return Connection
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    public static Connection get() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
