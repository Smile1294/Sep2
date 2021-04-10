package view;

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
  @FXML private TableColumn<SimpleCompanyViewModel, String> priceColumn;

  @Override protected void init()
  {
    errorLabel.textProperty().bindBidirectional(getViewModelFactory().getCompanyListViewModel().getErrorProperty());
    companyList.setItems(getViewModelFactory().getCompanyListViewModel().getList());
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
    priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asString());
  }

  public void onBack(ActionEvent actionEvent)
  {
  }

  public void onChoose(ActionEvent actionEvent)
  {

  }
}
