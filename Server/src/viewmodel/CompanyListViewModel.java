package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Company;
import model.Model;

/**
 * CompanyListViewModel is class for functionality of CompanyList view
 */

public class CompanyListViewModel
{
  private Model model;
  private ObservableList<SimpleCompanyViewModel> list;
  private ObjectProperty<SimpleCompanyViewModel> selectedSimpleCompany;
  private StringProperty errorProperty;
  private ViewState viewState;

  /**
   * Constructor that is initialising all the instance variables
   * @param model model for functionality
   * @param viewState viewState state of the account
   */

  public CompanyListViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    errorProperty = new SimpleStringProperty("");
    selectedSimpleCompany = new SimpleObjectProperty<>();
    this.viewState = viewState;
//    loadFromModel();
  }

  /**
   * clears the information and sets it to default
   */

  public void clear()
  {
    list.clear();
    errorProperty.setValue("");
    loadFromModel();
  }

  /**
   * loads companies from model
   */

  public void loadFromModel()
  {
    // load all companies
    for (Company c : model.getAllCompanies())
    {
      list.add(new SimpleCompanyViewModel(c));
    }
  }

  /**
   * gets error property
   * @return property
   */

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  /**
   * gets the list in the view
   * @return list
   */

  public ObservableList<SimpleCompanyViewModel> getList()
  {
    return list;
  }

  /**
   * gets company if its selected
   * @return true if selected
   */

  public boolean chose(){
    if (selectedSimpleCompany.get() != null){
      viewState.setSelectedSymbol(selectedSimpleCompany.get().getSymbol().get());
      return true;
    }
    errorProperty.setValue("No company selected");
    return false;
  }

  /**
   * sets selected company
   * @param companyVM company that is selected
   */

  public void setSelected(SimpleCompanyViewModel companyVM){
    selectedSimpleCompany = new SimpleObjectProperty<>(companyVM);
  }
}
