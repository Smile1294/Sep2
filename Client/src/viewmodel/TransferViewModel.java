package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.rmi.RemoteException;

/**
 * TransferViewModel is class for functionality of transfer view
 */

public class TransferViewModel {
    private Model model;
    private ViewState viewState;
    private StringProperty title;
    private StringProperty error;
    private DoubleProperty balance;
    private DoubleProperty amount;

    /**
     * Constructor that is initialising all the instance variables
     * @param model model for functionality
     * @param viewState viewState state of the account
     */

    public TransferViewModel(Model model, ViewState viewState)  {
        this.model = model;
        this.viewState = viewState;
        title = new SimpleStringProperty(viewState.isWithdraw()?"Withdraw cash":"Add cash");
        balance = new SimpleDoubleProperty();
        try {
            balance.setValue(Math.round(model.getBalance(viewState.getUserName())*100.0)/100.0);
        } catch (Exception e){
            e.printStackTrace();
        }
        amount = new SimpleDoubleProperty();
        error = new SimpleStringProperty();
    }

    /**
     * clears the information and sets it to default
     */

    public void clear()  {
        title.setValue(viewState.isWithdraw()?"Withdraw cash":"Add cash");
        try {
            balance.setValue(Math.round(model.getBalance(viewState.getUserName())*100.0)/100.0);
        } catch (Exception e){
            e.printStackTrace();
        }
        amount.setValue(null);
        error.setValue(null);
    }

    /**
     * confirms the money transfer
     */

    public void confirm(){
        try {
            model.transferMoney(viewState.getUserName(), amount.getValue(), viewState.isWithdraw());
            clear();
        } catch (Exception e){
            error.setValue(e.getMessage());
        }
    }

    /**
     * gets the title
     * @return title
     */

    public StringProperty titleProperty() {
        return title;
    }

    /**
     * gets error
     * @return error
     */

    public StringProperty errorProperty() {
        return error;
    }

    /**
     * gets balance
     * @return balance
     */

    public DoubleProperty balanceProperty() {
        return balance;
    }

    /**
     * gets amount
     * @return amount
     */

    public DoubleProperty amountProperty() {
        return amount;
    }
}
