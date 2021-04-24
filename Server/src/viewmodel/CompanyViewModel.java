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

  public CompanyViewModel(Model model, ViewState viewState){
    nameProperty = new SimpleStringProperty();
    priceProperty = new SimpleLongProperty();
    this.viewState = viewState;

  }
  public void clear()
  {
    nameProperty.setValue("");
    priceProperty.setValue(null);
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
