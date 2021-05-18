package view;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewmodel.SimpleOrderViewModel;


public class OrdersListController extends ViewController {
    public TableView<SimpleOrderViewModel> TableList;
    public TableColumn<SimpleOrderViewModel, String> Company;
    public TableColumn<SimpleOrderViewModel, String> Amount;
    public TableColumn<SimpleOrderViewModel, String> InitAmount;
    public TableColumn<SimpleOrderViewModel, String> Status;
    public TableColumn<SimpleOrderViewModel, String> Price;
    public TableColumn<SimpleOrderViewModel, String>  Buyingselling;


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
    public void Back(ActionEvent actionEvent) {
        getViewHandler().openView(View.ACCOUNT);
    }

    public void CancelOrder(ActionEvent actionEvent) {
        getViewModelFactory().getOrdersListViewModel().CloseOrder(TableList.getSelectionModel().getSelectedItem().getUuid());
    }
}
