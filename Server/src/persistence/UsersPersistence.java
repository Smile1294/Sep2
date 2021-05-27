package persistence;

import model.User;
import model.UserList;

import java.sql.SQLException;
/**
 * UsersPersistence is interface for loading and saving users
 */
public interface UsersPersistence {
    /**
     * loads all of users from database to @param users
     * @return users
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    UserList load() throws SQLException;
    /**
     * Updates specific user in database
     * @param user user to update in database
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void update(User user)throws SQLException;
    /**
     * Saves specific user to database
     * @param user user to save to database
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void save(User user)throws SQLException;
    /**
     * Removes specific user from database
     * @param user user to remove from database
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    void remove(User user)throws SQLException;
}
