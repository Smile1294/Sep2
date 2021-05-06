package model;


import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class UserList
{
  private List<User> users;

  public UserList()
  {
    users = new ArrayList<>();
  }


  public User getUser(UserName userName){
    for (User u : users){
      if (u.getUserName().equals(userName)){
        return u;
      }
    }
    return null;
  }

  public boolean addUser(User user) throws Exception {
    if (nameExist(user.getUserName())){
      throw new Exception("Username already exists");
    }
    users.add(user);
    return true;
  }

  public boolean nameExist(UserName userName){
    for(User x: users){
      if(x.getUserName().equals(userName)){
        return true;
      }
    }
    return false;
  }

  public boolean userExist(User user){
    return users.contains(user);
  }

  public double getBalance(UserName userName){
    for (User u : users){
      if (u.getUserName().equals(userName)){
        return u.getBalance();
      }
    }
    return 0.0;
  }
  public Stocks getStocks(UserName userName){
    for (User u : users){
      if (u.getUserName().equals(userName)){
        return u.getStocks();
      }
    }
    return null;
  }

  public void transferMoney(UserName userName, double amount, boolean isWithdraw){
    User user = null;
    for (User u : users){
      if (u.getUserName().equals(userName)){
        user = u;
      }
    }
    if (isWithdraw){
      user.withDraw(amount);
    }
    else {
      user.addCash(amount);
    }
  }

  @Override
  public String toString() {
    return "UserList{" +
            "users=" + users +
            '}';
  }
}
