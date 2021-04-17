package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class AccountViewModel {
    private Model model;
    private UserInformation userInformation;
    private StringProperty total,value,balance;

    public AccountViewModel(Model model, UserInformation userInformation){
        this.model = model;
        this.userInformation = userInformation;
        total = new SimpleStringProperty();
        value = new SimpleStringProperty();
        balance = new SimpleStringProperty();
    }

    public void clear(){

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
