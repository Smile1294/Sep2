package filePersistence;

import model.UserList;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;

/**
 * UserListFile class is implementing UserListPersistence for saving and loading users
 */

public class UserListFile implements UserListPersistence{
    private XmlJsonParser parser;

    /**
     * Constructor initialising instance variable
     */

    public UserListFile() {
        this.parser = new XmlJsonParser();
    }

    /**
     * saving users list into a file
     * @param userList list of users that is being stored
     * @param filename name of a file that is information stored on
     */


    @Override
    public void save(UserList userList, String filename) {
        File file = null;
        try {
            file = parser.toJson(userList, filename);
        } catch (ParserException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Saved to file: " + file.getAbsolutePath());
    }

    /**
     * loading users list from a file
     * @param fileName from file to load
     * @return user list
     */

    @Override
    public UserList load(String fileName) {
        UserList userList = new UserList();
        try {
            userList = parser.fromJson(fileName, UserList.class);
        } catch (ParserException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Loaded from file: " + fileName);
        return userList;
    }
}
