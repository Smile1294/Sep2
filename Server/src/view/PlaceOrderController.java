package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.NumberStringConverter;

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
        Bindings.bindBidirectional(priceField.textProperty(),
                getViewModelFactory().getPlaceOrderController().getPrice(),
                new NumberStringConverter());
        Bindings.bindBidirectional(amountField.textProperty(),
                getViewModelFactory().getPlaceOrderController().getAmount(),
                new NumberStringConverter());
        Bindings.bindBidirectional(ballanceLabel.textProperty(),
                getViewModelFactory().getPlaceOrderController().balanceProperty(),
                new NumberStringConverter());
        stockChoice.setValue("Apple Inc.");

    }

    @Override
    public void reset() {
        stockChoice.setValue("Apple Inc.");

    }

    public void onBuy(ActionEvent actionEvent) throws Exception {
        getViewModelFactory().getPlaceOrderController().buy(stockChoice.getSelectionModel().getSelectedItem().toString());
    }

    public void onSell(ActionEvent actionEvent) {
        getViewModelFactory().getPlaceOrderController().sell(stockChoice.getSelectionModel().getSelectedItem().toString());
    }

    public void onBack(ActionEvent actionEvent) {
        getViewHandler().openView(View.COMPANY_LIST);
    }

    public void Portfolio(ActionEvent actionEvent) {
        getViewHandler().openView(View.PORTFOLIO);
    }
}
