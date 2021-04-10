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
  private Chosen chosen;

  public CompanyViewModel(){
    nameProperty = new SimpleStringProperty();
    priceProperty = new SimpleLongProperty();
    chosen = Chosen.getInstance();
    load();
  }
  public void clear()
  {
    nameProperty.setValue("");
    priceProperty.setValue(null);
  }

  public void load()
  {
    clear();
    nameProperty.setValue(chosen.getName());
    priceProperty.setValue(chosen.getPrice());
  }

  public StringProperty getNameProperty()
  {
    nameProperty.setValue(chosen.getName());
    return nameProperty;
  }

  public LongProperty getPriceProperty()
  {
    priceProperty.setValue(chosen.getPrice());
    return priceProperty;
  }
}
