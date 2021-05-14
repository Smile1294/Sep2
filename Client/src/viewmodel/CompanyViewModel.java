package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Company;
import model.Model;

/**
 * CompanyViewModel is class for functionality of company view
 */

public class CompanyViewModel
{
  private StringProperty name;
  private StringProperty symbol;
  private DoubleProperty price;
  private ViewState viewState;
  private Model model;

  /**
   * Constructor that is initialising all the instance variables
   * @param model model for functionality
   * @param viewState viewState state of the account
   */

  public CompanyViewModel(Model model, ViewState viewState){
    this.viewState = viewState;
    this.model = model;
    name = new SimpleStringProperty();
    symbol = new SimpleStringProperty();
    price = new SimpleDoubleProperty();
  }

  /**
   * clears the information and sets it to default
   */

  public void clear()
  {
    System.out.println("viewState.getSelectedSymbol()"+viewState.getSelectedSymbol());
    Company company = model.getCompanyBySymbol(viewState.getSelectedSymbol());
    name.setValue(company.getName());
    symbol.setValue(company.getSymbol());
    price.setValue(Math.round(company.getCurrentPrice() *1000.0)/1000.0);
  }

  /**
   * loads
   */

  public void load()
  {

  }

  /**
   * gets name
   * @return name
   */

  public StringProperty getNameProperty()
  {
    return name;
  }

  /**
   * gets symbol
   * @return symbol
   */

  public StringProperty getSymbolProperty() {return symbol;}

  /**
   * gets price
   * @return price
   */

  public DoubleProperty getPriceProperty()
  {
    return price;
  }
}
