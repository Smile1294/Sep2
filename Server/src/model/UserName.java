package model;

public class UserName
{
  private String name;

  public UserName(String username)
  {
    if (username == null || username.length() < 3)
    {
      throw new IllegalArgumentException("Username must have at least 3 characters");
    }
    this.name = username;
  }

  public String getName()
  {
    return name;
  }

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

  @Override public String toString()
  {
    return name+"";
  }
}
