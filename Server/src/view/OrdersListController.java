package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.SimpleOrderViewModel;


public class OrdersListController extends ViewController {
    @FXML
    private TableView<SimpleOrderViewModel> TableList;
    @FXML
    private TableColumn<SimpleOrderViewModel, String> Company;
    @FXML
    private TableColumn<SimpleOrderViewModel, String> Amount;
    @FXML
    private TableColumn<SimpleOrderViewModel, String> InitAmount;
    @FXML
    private TableColumn<SimpleOrderViewModel, String> Status;
    @FXML
    private TableColumn<SimpleOrderViewModel, String> Price;
    @FXML
    private TableColumn<SimpleOrderViewModel, String>  Buyingselling;


    @Override
    protected void init() {
        Company.setCellValueFactory(cellData -> cellData.getValue().companyProperty());
        Amount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        InitAmount.setCellValueFactory(cellData -> cellData.getValue().initAmountProperty());
        Status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        Price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        Buyingselling.setCellValueFactory(cellData->cellData.getValue().isSelling());
        TableList.setItems(getViewModelFactory().getOrdersListViewModel().getSimpleOrderViewModels());

        reset();
    }
    @Override public void reset()
    {
        getViewModelFactory().getOrdersListViewModel().clear();
    }
    @FXML
    private void Back(ActionEvent actionEvent) {
        getViewHandler().openView(View.ACCOUNT);
    }

    @FXML
    private void CancelOrder(ActionEvent actionEvent) {
        getViewModelFactory().getOrdersListViewModel().CloseOrder(TableList.getSelectionModel().getSelectedItem().getUuid());
    }
}
