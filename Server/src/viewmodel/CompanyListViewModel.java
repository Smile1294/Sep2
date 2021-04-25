package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Company;
import model.Model;

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
    list.clear();
    errorProperty.setValue("");
    loadFromModel();
  }

  public void loadFromModel()
  {
    // load all companies
    for (Company c : model.getAllCompanies())
    {
      list.add(new SimpleCompanyViewModel(c));
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

  public boolean chose(){
    if (selectedSimpleCompany.get() != null){
      viewState.setSelectedSymbol(selectedSimpleCompany.get().getSymbol().get());
      return true;
    }
    errorProperty.setValue("No company selected");
    return false;
  }

  public void setSelected(SimpleCompanyViewModel companyVM){
    selectedSimpleCompany = new SimpleObjectProperty<>(companyVM);
  }
}
