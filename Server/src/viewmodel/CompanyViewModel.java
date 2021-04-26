package viewmodel;

import javafx.beans.property.*;
import model.Model;

public class CompanyViewModel
{
  private StringProperty name;
  private StringProperty symbol;
  private DoubleProperty price;
  private ViewState viewState;
  private Model model;

  public CompanyViewModel(Model model, ViewState viewState){
    this.viewState = viewState;
    this.model = model;
    name = new SimpleStringProperty();
    symbol = new SimpleStringProperty();
    price = new SimpleDoubleProperty();
  }

  public void clear()
  {
    name.setValue(model.getCompany(viewState.getSelectedSymbol()).getName());
    symbol.setValue(viewState.getSelectedSymbol());
    price.setValue(Math.round(model.getCompany(viewState.getSelectedSymbol()).getCurrentPrice()*1000.0)/1000.0);
  }

  public void load()
  {

  }

  public StringProperty getNameProperty()
  {
    return name;
  }

  public StringProperty getSymbolProperty() {return symbol;}

  public DoubleProperty getPriceProperty()
  {
    return price;
  }
}
