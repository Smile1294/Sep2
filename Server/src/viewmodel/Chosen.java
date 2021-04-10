package viewmodel;

public class Chosen
{
  private String name;
  private int price;

  public Chosen(){
    name = null;
    price = 0;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setPrice(int price)
  {
    this.price = price;
  }

  public String getName()
  {
    return name;
  }

  public int getPrice()
  {
    return price;
  }
}
