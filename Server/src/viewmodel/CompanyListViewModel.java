package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Stock;

public class CompanyListViewModel
{
  private Model model;
  private ObservableList<SimpleCompanyViewModel> list;
  private StringProperty errorProperty;

  public CompanyListViewModel(Model model)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    errorProperty = new SimpleStringProperty("");
    loadFromModel();
  }

  public void clear()
  {
    errorProperty.setValue("");
    list.clear();
  }

  public void loadFromModel()
  {
    clear();
    for (int x = 0; x < model.getStocks().getSize(); x++)
    {
      list.add(new SimpleCompanyViewModel(model.getStocks().getStock(x)));
    }
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public ObservableList<SimpleCompanyViewModel> getList()
  {
    return list;
  }
}
