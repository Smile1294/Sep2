package viewmodel;

import model.UserName;

public class ViewState {
    private UserName userName;
    private boolean withdraw;
    private String selectedSymbol;
    private boolean FromAccountView;

    public ViewState() {
    }

    public boolean isFromAccountView() {
        return FromAccountView;
    }

    public void setFromAccountView(boolean fromAccountView) {
        FromAccountView = fromAccountView;
    }

    public void setSelectedSymbol(String selectedSymbol) {
        this.selectedSymbol = selectedSymbol;
    }

    public String getSelectedSymbol() {
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

    public void setWithdraw(boolean withdraw) {
        this.withdraw = withdraw;
    }
}
