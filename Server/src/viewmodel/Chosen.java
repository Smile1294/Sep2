package viewmodel;

public class Chosen
{
  private String name;
  private long price;

  private static Chosen chosen = new Chosen();

  private Chosen()
  {
    name = "a";
    price = 0;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setPrice(long price)
  {
    this.price = price;
  }

  public String getName()
  {
    return name;
  }

  public long getPrice()
  {
    return price;
  }

  public static Chosen getInstance()
  {
    return chosen;
  }
}
