package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class TransferCashViewModel {
    private Model model;
    private UserInformation userInformation;
    private TransferState transferState;
    private StringProperty title;

    public TransferCashViewModel(Model model, UserInformation userInformation, TransferState transferState){
        this.model = model;
        this.userInformation = userInformation;
        this.transferState = transferState;
        title = new SimpleStringProperty(transferState.isWithdraw()?"Withdraw cash":"Add cash");

    }

    public void clear(){
        title.setValue(transferState.isWithdraw()?"Withdraw cash":"Add cash");
    }

    public StringProperty headerProperty() {
        return title;
    }
}
