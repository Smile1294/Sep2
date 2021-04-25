package viewmodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class CompanyViewModel
{
  private StringProperty nameProperty;
  private LongProperty priceProperty;
  private ViewState viewState;
  private Model model;

  public CompanyViewModel(Model model, ViewState viewState){
    this.viewState = viewState;
    this.model = model;
    nameProperty = new SimpleStringProperty();
    priceProperty = new SimpleLongProperty();

  }
  public void clear()
  {
    nameProperty.setValue(model.getCompany(viewState.getSelected()).getName());
    priceProperty.setValue(model.getCompany(viewState.getSelected()).getCurrentPrice());
  }

  public void load()
  {

  }

  public StringProperty getNameProperty()
  {
    return nameProperty;
  }

  public LongProperty getPriceProperty()
  {
    return priceProperty;
  }
}
