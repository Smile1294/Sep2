package utility;

import javafx.util.StringConverter;

public class NumberStringConverter extends StringConverter<Number>
{
  @Override
  public String toString(Number number) {
    return number == null || number.doubleValue() == 0.0 ? "" : number.toString();
  }

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