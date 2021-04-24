package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class TransferViewModel {
    private Model model;
    private ViewState viewState;
    private StringProperty title;
    private StringProperty error;
    private DoubleProperty balance;
    private DoubleProperty amount;

    public TransferViewModel(Model model, ViewState viewState){
        this.model = model;
        this.viewState = viewState;
        title = new SimpleStringProperty(viewState.isWithdraw()?"Withdraw cash":"Add cash");
        balance = new SimpleDoubleProperty(model.getBalance(viewState.getUserName()));
        amount = new SimpleDoubleProperty();
        error = new SimpleStringProperty();
    }

    public void clear(){
        title.setValue(viewState.isWithdraw()?"Withdraw cash":"Add cash");
        balance.setValue(model.getBalance(viewState.getUserName()));
        amount.setValue(null);
        error.setValue(null);
    }

    public void confirm(){
        try {
            model.transferMoney(viewState.getUserName(), amount.getValue(), viewState.isWithdraw());
        } catch (IllegalArgumentException e){
            error.setValue(e.getMessage());
        }
    }

    public StringProperty titleProperty() {
        return title;
    }
    public StringProperty errorProperty() {
        return error;
    }
    public DoubleProperty balanceProperty() {
        return balance;
    }
    public DoubleProperty amountProperty() {
        return amount;
    }
}
