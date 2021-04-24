package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Stocks;

public class CompanyListViewModel
{
  private Model model;
  private ObservableList<SimpleCompanyViewModel> list;
  private ObjectProperty<SimpleCompanyViewModel> selectedSimpleCompany;
  private StringProperty errorProperty;
  private ViewState viewState;

  public CompanyListViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    errorProperty = new SimpleStringProperty("");
    selectedSimpleCompany = new SimpleObjectProperty<>();
    this.viewState = viewState;
    loadFromModel();
  }

  public void clear()
  {
    errorProperty.setValue("");
    list.clear();
  }

  public void loadFromModel()
  {
//    clear();
//    for (int x = 0; x < model.getStocks().getSize(); x++)
//    {
//      list.add(new SimpleCompanyViewModel(model.getStocks().getStock(x)));
//    }
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public ObservableList<SimpleCompanyViewModel> getList()
  {
    return list;
  }

  public boolean chose(){
    return selectedSimpleCompany.get() != null;
  }

  public void setSelected(SimpleCompanyViewModel companyVM){
    selectedSimpleCompany = new SimpleObjectProperty<>(companyVM);
  }
}
