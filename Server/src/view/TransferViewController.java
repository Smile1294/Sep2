package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.NumberStringConverter;


public class TransferViewController extends ViewController{
    @FXML private Label titleLabel;
    @FXML private Label balanceLabel;
    @FXML private Label errorLabel;
    @FXML private TextField amountField;

    @Override
    protected void init() {
        titleLabel.textProperty().bind(getViewModelFactory().getTransferViewModel().titleProperty());
        Bindings.bindBidirectional(balanceLabel.textProperty(),
                getViewModelFactory().getTransferViewModel().balanceProperty(),
                new NumberStringConverter());
        Bindings.bindBidirectional(amountField.textProperty(),
                getViewModelFactory().getTransferViewModel().amountProperty(),
                new NumberStringConverter());
        errorLabel.textProperty().bind(getViewModelFactory().getTransferViewModel().errorProperty());
    }

    public void reset(){
        getViewModelFactory().getTransferViewModel().clear();
    }

    @FXML
    private void onBack(){
        getViewHandler().openView(View.ACCOUNT);
    }

    public void onConfirm(ActionEvent actionEvent) {
        getViewModelFactory().getTransferViewModel().confirm();
    }
}
