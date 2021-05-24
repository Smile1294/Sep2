package viewmodel;

import javafx.beans.property.*;
import model.Model;

/**
 * AccountViewModel is class for functionality of account view
 */

public class AccountViewModel {
    private Model model;
    private StringProperty user;
    private DoubleProperty total,value,balance;
    private ViewState viewState;

    /**
     * Constructor that is initialising all the instance variables
     * @param model model for functionality
     * @param viewState state of the account
     */

    public AccountViewModel(Model model, ViewState viewState){
        this.model = model;
        this.viewState = viewState;
        value = new SimpleDoubleProperty();
        total = new SimpleDoubleProperty();
        balance = new SimpleDoubleProperty();
        user = new SimpleStringProperty();
    }

    /**
     * clears the information and sets it to default
     */

    public void clear(){

        double invested = Math.round(model.getPriceTotal(viewState.getUserName().getName())*100.0)/100.0;
        double moneyBalance = Math.round(model.getBalance(viewState.getUserName())*100.0)/100.0;
        value.setValue(invested);
        total.setValue(Math.round((invested+moneyBalance)*100.0)/100.0);
        balance.setValue(moneyBalance);
        user.setValue(viewState.getUserName().toString());
    }

    /**
     * sets the state to withdraw
     */

    public void setWithdraw(){
        viewState.setWithdraw(true);
    }

    /**
     * sets the deposit by putting withdraw to false
     */

    public void setAdd(){
        viewState.setWithdraw(false);
    }

    /**
     * sets viewstate from account view to false is used when user is placing order
     * from company list
     */

    public void setViewStateBoolean()
    {
        viewState.setFromAccountView(false);
    }
    /**
     * getting total invested
     * @return total invested
     */
    public DoubleProperty totalProperty() {
        return total;
    }

    /**
     * getting value of investments
     * @return value of investments
     */

    public DoubleProperty valueProperty() {
        return value;
    }

    /**
     * getting the balance of account
     * @return balance of account
     */

    public DoubleProperty balanceProperty() {
        return balance;
    }

    /**
     * getting user of the account
     * @return user
     */

    public StringProperty userProperty() {
        return user;
    }
}
