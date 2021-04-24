package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class TransferViewModel {
    private Model model;
    private UserInformation userInformation;
    private TransferState transferState;
    private StringProperty title;
    private StringProperty error;
    private DoubleProperty balance;
    private DoubleProperty amount;

    public TransferViewModel(Model model, UserInformation userInformation, TransferState transferState){
        this.model = model;
        this.userInformation = userInformation;
        this.transferState = transferState;
        title = new SimpleStringProperty(transferState.isWithdraw()?"Withdraw cash":"Add cash");
        balance = new SimpleDoubleProperty(model.getBalance(userInformation.getUser()));
        amount = new SimpleDoubleProperty();
        error = new SimpleStringProperty();
    }

    public void clear(){
        title.setValue(transferState.isWithdraw()?"Withdraw cash":"Add cash");
        balance.setValue(model.getBalance(userInformation.getUser()));
        amount.setValue(null);
        error.setValue(null);
    }

    public void confirm(){
        if (transferState.isWithdraw()){
            model.fuckYourMoney(amount.doubleValue());
        }
        else {
            model.smellyMess(amount.doubleValue());
        }
        clear();
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
