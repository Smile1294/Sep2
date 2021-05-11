package persistence;

import model.User;
import model.UserList;

import java.sql.SQLException;

public interface UsersPersistence {
    UserList load() throws SQLException;
    void update(User user)throws SQLException;
    void save(User user)throws SQLException;
    void remove(User user)throws SQLException;
}
