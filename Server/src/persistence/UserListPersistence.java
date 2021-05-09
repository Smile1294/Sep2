package persistence;

import model.UserList;

/**
 * UserListPersistence is interface for loading and saving users
 */

public interface UserListPersistence {

    /**
     * saving users list into a file
     * @param userList list of users that is being stored
     * @param filename name of a file that is information stored on
     */

    void save(UserList userList, String filename);

    /**
     * loading users list from a file
     * @param fileName from file to load
     * @return user list
     */

    UserList load(String fileName);
}
