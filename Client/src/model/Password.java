package model;

import java.io.Serializable;

/**
 * Password class represents password of the user
 */

public class Password implements Serializable
{
  private String password;

  /**
   * Constructor that is setting instance variable
   * @param password password that user is setting
   */
  public Password(String password)
  {
    if (password == null)
    {
      throw new IllegalArgumentException("Null password");
    }
    String message = Password.isLegal(password);
    if (message != null)
    {
      throw new IllegalArgumentException(message);
    }
    this.password = password;
  }

  /**
   * checking if the password legal
   * @param password password that is being checked
   * @return legal or not password
   */

  public static boolean isLegalPassword(String password)
  {
    return isLegal(password) == null;
  }

  /**
   * checks if the password legal
   * @param password password that is being checked
   * @return legal or not password
   */

  private static String isLegal(String password)
  {
    if (password == null || password.length() < 6)
    {
      return "Password must have at least 6 characters";
    }
    int lower = 0;
    int upper = 0;
    int digit = 0;
    int special = 0;
    for (int i=0; i<password.length(); i++)
    {
      char ch = password.charAt(i);
      if (Character.isDigit(ch))
      {
        digit++;
      }
      else if (Character.isLowerCase(ch) && Character.isLetter(ch))
      {
        lower++;
      }
      else if (Character.isUpperCase(ch) && Character.isLetter(ch))
      {
        upper++;
      }
      else if (ch == '_' || ch == '-')
      {
        special++;
      }
    }
    if (lower + upper + digit + special < password.length())
    {
      return "Password may only contain letters, digits, hyphens amd underscore characters";
    }
    if (lower == 0 || upper == 0 || digit == 0)
    {
      return "Password must contain at least one uppercase letter, at least one lowercase letter and at least one digit";
    }

    return null;
  }

  /**
   * getting the password
   * @return password
   */

  public String getPassword()
  {
    return password;
  }

  /**
   * comparing object to the password
   * @param o object that is being compared
   * @return password
   */

  @Override
  public boolean equals(Object o){
    if (o == null){
      return false;
    }
    if(o == this){
      return true;
    }
    if (o instanceof Password){
      Password other = (Password) o;
      return password.equals(other.password);
    }
    return false;
  }

  /**
   * toString version of password
   * @return password
   */

  @Override public String toString()
  {
    return password;
  }

}
