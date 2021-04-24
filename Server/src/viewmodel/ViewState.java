package viewmodel;

import model.Company;
import model.UserName;

public class ViewState {
    private UserName userName;
    private boolean withdraw;
    private Company selected;

    public ViewState(){

    }

    public void setSelected(Company selected) {
        this.selected = selected;
    }

    public Company getSelected() {
        return selected;
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
