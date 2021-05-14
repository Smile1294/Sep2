package model;

/**
 * UserName class represents username of the user
 */

public class UserName
{
  private String name;

  /**
   * The constructor that is setting the username
   * @param username username of the user
   */

  public UserName(String username)
  {
    if (username == null || username.length() < 3)
    {
      throw new IllegalArgumentException("Username must have at least 3 characters");
    }
    this.name = username;
  }

  /**
   * getting the name
   * @return the name
   */

  public String getName()
  {
    return name;
  }

  /**
   * Comparing an object to the username
   * @param o the object that is getting compared with
   * @return name
   */

  @Override
  public boolean equals(Object o){
    if(o == this){
      return true;
    }
    if (o==null){
      return false;
    }
    if (o instanceof UserName){
      UserName other = (UserName) o;
      return name.equals(other.getName());
    }
    return false;
  }

  /**
   * toString version of the name
   * @return name
   */

  @Override public String toString()
  {
    return name+"";
  }
}
