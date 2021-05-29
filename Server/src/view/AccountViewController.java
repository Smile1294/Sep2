package view;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import utility.NumberStringConverter;

public class AccountViewController extends ViewController {
    @FXML
    private Label accountTotal;
    @FXML
    private Label accountBalance;
    @FXML
    private Label accountValue;
    @FXML
    private MenuButton menu;

    @Override
    protected void init() {
        Bindings.bindBidirectional(accountTotal.textProperty(),
                getViewModelFactory().getAccountViewModel().totalProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(accountBalance.textProperty(),
                getViewModelFactory().getAccountViewModel().balanceProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(accountValue.textProperty(),
                getViewModelFactory().getAccountViewModel().valueProperty(), new NumberStringConverter());
        menu.textProperty().bind(getViewModelFactory().getAccountViewModel().userProperty());
        reset();
    }

    public void reset() {
        getViewModelFactory().getAccountViewModel().clear();
    }

    @FXML
    private void onorderList() {
        getViewHandler().openView(View.ORDER_LIST);
    }

    @FXML
    private void onPortfolio() {
        getViewHandler().openView(View.PORTFOLIO);
    }

    @FXML
    private void onList() {
        getViewHandler().openView(View.COMPANY_LIST);
    }

    @FXML
    private void onWithdraw() {
        getViewModelFactory().getAccountViewModel().setWithdraw();
        getViewHandler().openView(View.TRANSFER);
    }

    @FXML
    private void onAdd() {
        getViewModelFactory().getAccountViewModel().setAdd();
        getViewHandler().openView(View.TRANSFER);
    }

    @FXML
    private void onPlaceOrder() {
        getViewModelFactory().getAccountViewModel().setViewStateBoolean();
        getViewHandler().openView(View.PLACE_ORDER);
    }

    @FXML
    private void onLogout() {
        getViewHandler().openView(View.LOGIN);
    }

    @FXML
    private void onQuit() {
        Platform.exit();
    }
}
