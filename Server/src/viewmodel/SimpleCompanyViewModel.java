package viewmodel;

import javafx.beans.property.*;
import model.Stock;

public class SimpleCompanyViewModel
{
  private StringProperty name;
  private LongProperty price;

  public SimpleCompanyViewModel(Stock stock){
    name = new SimpleStringProperty(stock.getName());
    price = new SimpleLongProperty(stock.getPrice());
  }

  public StringProperty getName()
  {
    return name;
  }

  public LongProperty getPrice()
  {
    return price;
  }
}
