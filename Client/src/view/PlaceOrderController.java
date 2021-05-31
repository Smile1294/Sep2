package view;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import utility.NumberStringConverter;

import java.rmi.RemoteException;
import java.util.regex.Pattern;

public class PlaceOrderController extends ViewController {
    @FXML
    private ChoiceBox<String> stockChoice;
    @FXML
    private BarChart ordersChart;
    @FXML
    private TextField priceField;
    @FXML
    private TextField amountField;
    @FXML
    private Label totalLabel;
    @FXML
    private Label ballanceLabel;
    @FXML
    private Label ErrorLable;
    @FXML
    private Label CurrentPrice;

    @Override
    protected void init() throws RemoteException {

        stockChoice.setItems(getViewModelFactory().getPlaceOrderViewModel().getStockChoice());
        CurrentPrice.textProperty().bind(getViewModelFactory().getPlaceOrderViewModel().getCurrentPrice());
        Bindings.bindBidirectional(priceField.textProperty(),
                getViewModelFactory().getPlaceOrderViewModel().getPrice(),
                new NumberStringConverter());
        stockChoice.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            getViewModelFactory().getPlaceOrderViewModel().UpdateCurrentPrice(newValue);
        });
        Bindings.bindBidirectional(stockChoice.valueProperty(), getViewModelFactory().getPlaceOrderViewModel().currentCompanySelectedProperty());
        Bindings.bindBidirectional(amountField.textProperty(), getViewModelFactory().getPlaceOrderViewModel().getAmount(), new NumberStringConverter());
        Bindings.bindBidirectional(ballanceLabel.textProperty(), getViewModelFactory().getPlaceOrderViewModel().balanceProperty());

        reset();

    }

    @Override
    public void reset() {
        try {
            getViewModelFactory().getPlaceOrderViewModel().reset();
            stockChoice.setValue(getViewModelFactory().getPlaceOrderViewModel().getSelectedCompany());
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void onBuy(ActionEvent actionEvent) throws Exception {
        getViewModelFactory().getPlaceOrderViewModel().buy(stockChoice.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void onSell(ActionEvent actionEvent) {
        getViewModelFactory().getPlaceOrderViewModel().sell(stockChoice.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void onBack(ActionEvent actionEvent) {
        if (getViewModelFactory().getPlaceOrderViewModel().Back()) {
            getViewHandler().openView(View.COMPANY_LIST);
        } else {
            getViewHandler().openView(View.ACCOUNT);
        }
    }

    @FXML
    private void Portfolio(ActionEvent actionEvent) {
        getViewHandler().openView(View.PORTFOLIO);
    }

    @FXML
    private void totalOnKeyTyped(KeyEvent keyEvent) {
        try {
            if (Pattern.compile("^[0-9]\\d*(\\.\\d+)?$").matcher(priceField.getText()).matches()) {
                if (!"".equals(amountField.getText())) {
                    ErrorLable.setText("");
                    totalLabel.setText(String.valueOf(Math.round((Integer.parseInt(amountField.getText()) * Double.parseDouble(priceField.getText())) * 100.0) / 100.0));
                }
            } else {
                totalLabel.setText("0");
                ErrorLable.setText("Invalid input string");
            }
        } catch (NumberFormatException e) {
            totalLabel.setText("0");
            ErrorLable.setText("Invalid input string");
        }
    }
}
