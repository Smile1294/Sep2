package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class TransferCashController extends ViewController{
    @FXML private Label header;

    @Override
    protected void init() {
        header.textProperty().bind(getViewModelFactory().getTransferCashViewModel().headerProperty());
    }

    public void reset(){
        getViewModelFactory().getTransferCashViewModel().clear();
    }

    @FXML
    private void onBack(){
        getViewHandler().openView(View.ACCOUNT);
    }
}
