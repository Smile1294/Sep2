package viewmodel;

import javafx.beans.property.*;
import model.Model;
import model.Stock;

public class AccountViewModel {
    private Model model;
    private StringProperty user;
    private DoubleProperty total,value,balance;
    private ViewState viewState;

    public AccountViewModel(Model model, ViewState viewState){
        this.model = model;
        this.viewState = viewState;
        value = new SimpleDoubleProperty();
        total = new SimpleDoubleProperty();
        balance = new SimpleDoubleProperty();
        user = new SimpleStringProperty();
    }

    public void clear(){
        value.setValue(null);
        total.setValue(null);
        balance.setValue(model.getBalance(viewState.getUserName()));
        user.setValue(viewState.getUserName().toString());
    }

    public void setWithdraw(){
        viewState.setWithdraw(true);
    }

    public void setAdd(){
        viewState.setWithdraw(false);
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public DoubleProperty balanceProperty() {
        return balance;
    }

    public StringProperty userProperty() {
        return user;
    }
}
