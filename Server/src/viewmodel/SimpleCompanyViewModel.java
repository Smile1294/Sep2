package viewmodel;

import javafx.beans.property.*;
import model.Company;
import model.Stock;

import java.text.DecimalFormat;

public class SimpleCompanyViewModel
{
  private StringProperty name;
  private StringProperty symbol;
  private DoubleProperty price;

  public SimpleCompanyViewModel(Company company){

    name = new SimpleStringProperty(company.getName());
    symbol = new SimpleStringProperty(company.getSymbol());
    price = new SimpleDoubleProperty(Math.round(company.getCurrentPrice()*1000.0)/1000.0);
  }

  public StringProperty getName()
  {
    return name;
  }

  public StringProperty getSymbol(){return symbol;}

  public DoubleProperty getPrice()
  {
    return price;
  }
}
