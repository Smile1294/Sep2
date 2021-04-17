package view;

import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PlaceOrderController extends ViewController {
    public ChoiceBox stockChoice;
    public BarChart ordersChart;
    public TextField priceField;
    public TextField amountField;
    public Label totalLabel;
    public Label ballanceLabel;

    @Override
    protected void init() {
        stockChoice.setItems(getViewModelFactory().getPlaceOrderController().getStockChoice());
        stockChoice.setValue("Apple");
        priceField.textProperty().bindBidirectional(getViewModelFactory().getPlaceOrderController().getPrice());
        amountField.textProperty().bindBidirectional(getViewModelFactory().getPlaceOrderController().getAmount());
        ballanceLabel.textProperty().bindBidirectional(getViewModelFactory().getPlaceOrderController().balanceProperty());


    }

    public void onBuy(ActionEvent actionEvent) throws Exception {
        getViewModelFactory().getPlaceOrderController().buy(stockChoice.getSelectionModel().getSelectedItem().toString());
    }

    public void onSell(ActionEvent actionEvent) {
        getViewModelFactory().getPlaceOrderController().sell(stockChoice.getSelectionModel().getSelectedItem().toString());
    }

    public void onBack(ActionEvent actionEvent) {
    }

    public void Portfolio(ActionEvent actionEvent) {
        getViewHandler().openView(View.PORTFOLIO);
    }
}
