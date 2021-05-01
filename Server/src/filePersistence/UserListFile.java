package filePersistence;

import model.UserList;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.File;

public class UserListFile implements UserListPersistence{
    private XmlJsonParser parser;

    public UserListFile() {
        this.parser = new XmlJsonParser();
    }

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
