package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class TransferCashController extends ViewController{
    @FXML private Label titleLabel;
    @FXML private Label balanceLabel;
    @FXML private TextField amountField;

    @Override
    protected void init() {
        titleLabel.textProperty().bind(getViewModelFactory().getTransferCashViewModel().headerProperty());
    }

    public void reset(){
        getViewModelFactory().getTransferCashViewModel().clear();
    }

    @FXML
    private void onBack(){
        getViewHandler().openView(View.ACCOUNT);
    }
}
