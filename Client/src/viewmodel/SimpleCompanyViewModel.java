package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Company;

/**
 * SimpleCompanyViewModel is class for functionality of company
 */

public class SimpleCompanyViewModel
{
  private StringProperty name;
  private StringProperty symbol;
  private DoubleProperty price;

  /**
   * Constructor initialising all the instance variables
   * @param company wanted company
   */

  public SimpleCompanyViewModel(Company company){

    name = new SimpleStringProperty(company.getName());
    symbol = new SimpleStringProperty(company.getSymbol());
    price = new SimpleDoubleProperty(Math.round(company.getCurrentPrice()*1000.0)/1000.0);
  }

  /**
   * gets name
   * @return name
   */

  public StringProperty getName()
  {
    return name;
  }

  /**
   * gets symbol
   * @return symbol
   */

  public StringProperty getSymbol(){return symbol;}

  /**
   * gets price
   * @return price
   */

  public DoubleProperty getPrice()
  {
    return price;
  }
}
