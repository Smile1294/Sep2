package utility;

import javafx.util.StringConverter;

/**
 * NumberStringConverter is converting between strings and numbers and vise versa
 */

public class NumberStringConverter extends StringConverter<Number>
{

  /**
   * converting number to string
   * @param number number that is getting converted
   * @return string version of the number
   */

  @Override
  public String toString(Number number) {
    return number == null || number.doubleValue() == 0.0 ? "" : number.toString();
  }

  /**
   * converting string to double
   * @param s string that is getting converted
   * @return double version of the string
   */

  @Override public Double fromString(String s)
  {
    try
    {
      return Double.parseDouble(s);
    }
    catch (Exception e)
    {
      return 0.0;
    }
  }
}