package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import model.Model;
import model.Price;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * CompanyViewModel is class for functionality of company view
 */

public class CompanyViewModel implements PropertyChangeListener
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
    name.setValue(model.getCompanyBySymbol(viewState.getSelectedSymbol()).getName());
    symbol.setValue(viewState.getSelectedSymbol());
    price.setValue(Math.round(model.getCompanyBySymbol(viewState.getSelectedSymbol()).getCurrentPrice() *1000.0)/1000.0);
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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    try
    {
      if (symbol.get().equals(evt.getPropertyName()))
      {
        Platform.runLater(() -> {
          price.setValue(((Price) evt.getNewValue()).getPrice());
        });
      }
    }
    catch(NullPointerException e){

    }
  }
}
