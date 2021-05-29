package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import utility.NumberStringConverter;
import viewmodel.PortfolioViewModel;
import viewmodel.SimpleStockViewModel;

public class PortfolioViewController extends ViewController {
    @FXML
    private TableView<SimpleStockViewModel> TableList;
    @FXML
    private TableColumn<SimpleStockViewModel, String> StockColumn;
    @FXML
    private TableColumn<SimpleStockViewModel, String> InvestedCloumn;
    @FXML
    private TableColumn<SimpleStockViewModel, String> Curentcolumn;
    @FXML
    private TableColumn<SimpleStockViewModel, String> percentcolumn;
    @FXML
    private TableColumn<SimpleStockViewModel, String> numberownedcolumn;
    @FXML
    private Label TotalAmount;
    @FXML
    private Label UserName;

    @Override
    protected void init() {
        UserName.textProperty().bind(getViewModelFactory().getPortfolioViewModel().getName());
        Bindings.bindBidirectional(TotalAmount.textProperty(),
                getViewModelFactory().getPortfolioViewModel().getPriceTotal(),
                new NumberStringConverter());
        Curentcolumn.setCellValueFactory(cellData -> cellData.getValue().getCurrentValue());
        InvestedCloumn.setCellValueFactory(cellData -> cellData.getValue().getinvested());
        StockColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        percentcolumn.setCellValueFactory(cellData -> cellData.getValue().getPercentage());
        numberownedcolumn.setCellValueFactory(cellData -> cellData.getValue().getNumberowned());
        TableList.setItems(getViewModelFactory().getPortfolioViewModel().getAll());
        reset();
    }
    @Override public void reset()
    {
        getViewModelFactory().getPortfolioViewModel().clear();
    }


    @FXML
    private void GoBack(ActionEvent actionEvent) {
        getViewHandler().openView(View.ACCOUNT);
    }

}
