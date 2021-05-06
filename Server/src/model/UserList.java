package model;


import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * UserList class represents list of users
 */

public class UserList {
    private List<User> users;

    /**
     * A constructor which is initialising arraylist of type User
     */

    public UserList() {
        users = new ArrayList<>();
    }

  /**
   * Finds a user from a user list
   *
   * @param userName userName of the user
   * @return userName of the user
   */

  public User getUser(UserName userName){
    for (User u : users){
      if (u.getUserName().equals(userName)){
        return u;
      }
    }
    return null;
  }

    /**
     * @param user new User that is added to the list
     * @return returns true
     * @throws Exception if the user isn't found
     */

    public boolean addUser(User user) throws Exception {
        if (nameExist(user.getUserName())) {
            throw new Exception("Username already exists");
        }
        users.add(user);
        return true;
    }

    /**
     * checks if the userName  exists
     *
     * @param userName userName of the user
     * @return returns true
     */

    public boolean nameExist(UserName userName) {
        for (User x : users) {
            if (x.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the user exists
     * @param user User that is getting checked
     * @return user
     */

    public boolean userExist(User user) {
        return users.contains(user);
    }

    /**
     * Getting the user balance
     * @param userName Username of the user
     * @return users balance
     */

    public double getBalance(UserName userName) {
        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                return u.getBalance();
            }
        }
        return 0.0;
    }

    /**
     * Getting the user stocks
     * @param userName Username of the user
     * @return users stocks
     */

    public Stocks getStocks(UserName userName) {
        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                return u.getStocks();
            }
        }
        return null;
    }

    /**
     * Withdrawing or depositing money
     * @param userName Username of the user that is transferring money
     * @param amount amount that is getting transferred
     * @param isWithdraw if its withdrawing or depositing
     */

    public void transferMoney(UserName userName, double amount, boolean isWithdraw) {
        User user = null;
        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                user = u;
            }
        }
        if (isWithdraw) {
            user.withDraw(amount);
        } else {
            user.addCash(amount);
        }
    }

    /**
     * toString version of the users
     *
     * @return users
     */

    @Override
    public String toString() {
        return "UserList{" +
                "users=" + users +
                '}';
    }
}
