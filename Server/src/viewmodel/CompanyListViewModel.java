package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Company;
import model.Message;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

/**
 * CompanyListViewModel is class for functionality of CompanyList view
 */

public class CompanyListViewModel implements LocalListener<String, Message>
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
    model.addListener(this,"Price");
    loadFromModel();
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
   * sets viewstate from account view on true so that when user is pressing back button in placing order will be put back to
   * companies list
   */

  public void setViewStateBoolean()
  {
    viewState.setFromAccountView(true);
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

  /**
   * if there is updated new Price the propertyChange will update view and model
   * @param event
   */
  @Override public void propertyChange(ObserverEvent<String, Message> event)
  {

      for (SimpleCompanyViewModel s : list)
      {
        if (s.getSymbol().get().equals(event.getValue1()))
        {
          model.getCompanyBySymbol(event.getValue2().getPriceObject().getSymbol()).setCurrentPrice(event.getValue2().getPriceObject().getPrice());
          Platform.runLater(() -> {
            s.getPrice().setValue(event.getValue2().getPriceObject().getPrice());
          });
        }
      }

  }
}
