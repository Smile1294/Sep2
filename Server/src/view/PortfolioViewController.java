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
    public TableView<SimpleStockViewModel> TableList;
    public TableColumn<SimpleStockViewModel, String> StockColumn;
    public TableColumn<SimpleStockViewModel, String> InvestedCloumn;
    public TableColumn<SimpleStockViewModel, String> Curentcolumn;
    public TableColumn<SimpleStockViewModel, String> percentcolumn;
    public TableColumn<SimpleStockViewModel, String> numberownedcolumn;
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


    public void GoBack(ActionEvent actionEvent) {
        getViewHandler().openView(View.ACCOUNT);
    }

}
