package viewmodel;

import javafx.beans.property.*;
import model.Model;
import model.Stock;

public class AccountViewModel {
    private Model model;
    private UserInformation userInformation;
    private StringProperty user;
    private DoubleProperty total,value,balance;
    private TransferState transferState;

    public AccountViewModel(Model model, UserInformation userInformation, TransferState transferState){
        this.model = model;
        this.userInformation = userInformation;
        this.transferState = transferState;
        value = fuckThisSmells();
        total = new SimpleDoubleProperty(model.getUser().getBalance()+ value.doubleValue());
        balance = new SimpleDoubleProperty(model.getUser().getBalance());
        user = new SimpleStringProperty(model.getUser().getName());
    }

    public void clear(){
        value = fuckThisSmells();
        total.setValue(model.getUser().getBalance()+ value.doubleValue());
        balance.setValue(model.getUser().getBalance());
        user.setValue(model.getUser().getName());
    }

    private DoubleProperty fuckThisSmells(){
            double d = 0.0;
            try {
                for (Stock s : model.getAllStocks()) {
                    if (model.getUser().getSpecific(s.getName()).getAmount() > 0) {
                        d = d + s.getPrice() * model.getUser().getSpecific(s.getName()).getAmount();
                        System.out.println(d);
                    }
                }
                return new SimpleDoubleProperty(d);
            } catch (Exception e) {
                System.out.println(e);
            }
            return new SimpleDoubleProperty(d);
    }

    public void setWithdraw(){
        transferState.setWithdraw(true);
    }

    public void setAdd(){
        transferState.setWithdraw(false);
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

    public void setFromProfile() {
        transferState.setFromCompanyInfo(false);
    }
}
