package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccountViewController extends ViewController{
    @FXML private Label accountTotal;
    @FXML private Label accountBalance;
    @FXML private Label accountValue;

    @Override
    protected void init() {
        accountTotal.textProperty().bind(getViewModelFactory().getAccountViewModel().totalProperty());
        accountBalance.textProperty().bind(getViewModelFactory().getAccountViewModel().balanceProperty());
        accountValue.textProperty().bind(getViewModelFactory().getAccountViewModel().valueProperty());
    }

    public void reset(){
        getViewModelFactory().getAccountViewModel().clear();
    }

    @FXML
    private void onPortfolio(){

    }

    @FXML
    private void onLogout(){
        getViewHandler().openView(View.LOGIN);
    }

    @FXML
    private void onQuit(){
        Platform.exit();
    }
}
