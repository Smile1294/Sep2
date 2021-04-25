package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.SimpleCompanyViewModel;

public class CompanyListViewController extends ViewController
{
  @FXML private Label errorLabel;
  @FXML private TableView<SimpleCompanyViewModel> companyList;
  @FXML private TableColumn<SimpleCompanyViewModel, String> nameColumn;
  @FXML private TableColumn<SimpleCompanyViewModel, String> symbolColumn;
  @FXML private TableColumn<SimpleCompanyViewModel, Number> priceColumn;

  @Override protected void init()
  {
    errorLabel.textProperty().bindBidirectional(getViewModelFactory().getCompanyListViewModel().getErrorProperty());
    companyList.getSelectionModel().selectedItemProperty().addListener(
      (obs, oldVal, newVal) -> getViewModelFactory().getCompanyListViewModel().setSelected(newVal)
  );
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
    symbolColumn.setCellValueFactory(cellData -> cellData.getValue().getSymbol());
    priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice());
    companyList.setItems(getViewModelFactory().getCompanyListViewModel().getList());

  }

  @Override public void reset()
  {
    getViewModelFactory().getCompanyListViewModel().clear();
  }

  public void onBack(ActionEvent actionEvent)
  {

    getViewHandler().openView(View.ACCOUNT);
  }

  public void onChoose(ActionEvent actionEvent)
  {
      if(getViewModelFactory().getCompanyListViewModel().chose())
      {
        getViewHandler().openView(View.COMPANY_VIEW);
      }
  }
}
