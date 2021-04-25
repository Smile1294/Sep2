package viewmodel;

import model.Company;
import model.UserName;

public class ViewState {
    private UserName userName;
    private boolean withdraw;
    private String selectedSymbol;

    public ViewState(){

    }

    public void setSelected(String selectedSymbol) {
        this.selectedSymbol = selectedSymbol;
    }

    public String getSelected() {
        return selectedSymbol;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public UserName getUserName() {
        return userName;
    }

    public boolean isWithdraw() {
        return withdraw;
    }

    public void setWithdraw(boolean withdraw){
        this.withdraw = withdraw;
    }
}
