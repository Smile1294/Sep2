package model;

import persistence.UserFilePersistence;
import persistence.UserListFile;
import java.io.*;
import java.sql.*;
import java.util.List;

public class UserList
{
  private List<User> userList;
  private UserFilePersistence filePersistence;
  private Connection con;
  PreparedStatement pst;

  public UserList()
  {
    con = null;
    pst = null;
    filePersistence = new UserListFile("Profiles.txt");
    try{
      userList = filePersistence.load();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  public boolean addProfile(String name, String password) throws Exception
  {
    if (nameExist(name)){
      throw new Exception("Username already exists");
    }
    User user = new User(name,password);
    userList.add(user);
    filePersistence.addUser(user);
    return true;
  }

  public boolean nameExist(String name){
    for(User x: userList){
      if(x.getName().equals(name)){
        return true;
      }
    }
    return false;
  }

  public boolean userExistSQL(String name, String password) throws SQLException {
    try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","")) {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM testingofjavasaving.userofapplication WHERE name = ? AND password = ?");
      statement.setString(1, name);
      statement.setString(2,password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        System.out.println(resultSet);
        return true;
      } else {
        return false;
      }
    }
  }



  public boolean userExist(String name, String password){
    for(User x: userList){
      if(x.getName().equals(name) && x.getPassword().equals(password)){
        return true;
      }
    }
    return false;
  }
}
