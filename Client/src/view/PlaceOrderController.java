package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import utility.NumberStringConverter;

import java.rmi.RemoteException;

public class PlaceOrderController extends ViewController {
    public ChoiceBox<String> stockChoice;
    public BarChart ordersChart;
    public TextField priceField;
    public TextField amountField;
    public Label totalLabel;
    public Label ballanceLabel;
    public Label ErrorLable;

    @Override
    protected void init() throws RemoteException {
        stockChoice.setItems(getViewModelFactory().getPlaceOrderViewModel().getStockChoice());
        Bindings.bindBidirectional(priceField.textProperty(),
                getViewModelFactory().getPlaceOrderViewModel().getPrice(),
                new NumberStringConverter());
        Bindings.bindBidirectional(amountField.textProperty(),
                getViewModelFactory().getPlaceOrderViewModel().getAmount(),
                new NumberStringConverter());
        Bindings.bindBidirectional(ballanceLabel.textProperty(),
                getViewModelFactory().getPlaceOrderViewModel().balanceProperty());
        reset();

    }

    @Override
    public void reset() {
        stockChoice.setValue(getViewModelFactory().getPlaceOrderViewModel().getSelectedCompany());
        getViewModelFactory().getPlaceOrderViewModel().reset();
    }

    public void onBuy(ActionEvent actionEvent) throws Exception {
        getViewModelFactory().getPlaceOrderViewModel().buy(stockChoice.getSelectionModel().getSelectedItem().toString());
    }

    public void onSell(ActionEvent actionEvent) {
        getViewModelFactory().getPlaceOrderViewModel().sell(stockChoice.getSelectionModel().getSelectedItem().toString());
    }

    public void onBack(ActionEvent actionEvent) {
        if (getViewModelFactory().getPlaceOrderViewModel().Back()) {
            getViewHandler().openView(View.COMPANY_LIST);
        } else {
            getViewHandler().openView(View.ACCOUNT);
        }
    }

    public void Portfolio(ActionEvent actionEvent) {
        getViewHandler().openView(View.PORTFOLIO);
    }

    public void PriceonKeyTyped(KeyEvent keyEvent) {
        try {
            if (!"".equals(amountField.getText()) && !"".equals(priceField.getText())) {
                Integer.parseInt(amountField.getText());
                ErrorLable.setText("");
                totalLabel.setText(String.valueOf(Integer.parseInt(priceField.getText()) * Integer.parseInt(amountField.getText())));
            } else {
                totalLabel.setText("0");
            }
        } catch (NumberFormatException e) {
            totalLabel.setText("0");
            ErrorLable.setText("Input string cannot be parsed to integer");
        }

    }

    public void AmountOnKeyTyped(KeyEvent keyEvent) {
        try {
            if (!"".equals(amountField.getText()) && !"".equals(priceField.getText())) {
                Integer.parseInt(amountField.getText());
                ErrorLable.setText("");
                totalLabel.setText(String.valueOf(Integer.parseInt(priceField.getText()) * Integer.parseInt(amountField.getText())));
            } else {
                totalLabel.setText("0");
            }
        } catch (NumberFormatException e) {
            totalLabel.setText("0");
            ErrorLable.setText("Input string cannot be parsed to integer");
        }
    }
}
