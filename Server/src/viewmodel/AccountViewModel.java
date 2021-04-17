package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class AccountViewModel {
    private Model model;
    private UserInformation userInformation;
    private StringProperty total,value,balance;
    private TransferState transferState;

    public AccountViewModel(Model model, UserInformation userInformation, TransferState transferState){
        this.model = model;
        this.userInformation = userInformation;
        this.transferState = transferState;
        total = new SimpleStringProperty();
        value = new SimpleStringProperty();
        balance = new SimpleStringProperty();
    }

    public void clear(){

    }

    public void setWithdraw(){
        transferState.setWithdraw(true);
    }

    public void setAdd(){
        transferState.setWithdraw(false);
    }

    public StringProperty totalProperty() {
        return total;
    }

    public StringProperty valueProperty() {
        return value;
    }

    public StringProperty balanceProperty() {
        return balance;
    }
}
