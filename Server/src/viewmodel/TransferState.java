package viewmodel;

public class TransferState {
    private boolean withdraw;
    private boolean fromCompanyInfo;

    public TransferState(){
        withdraw = true;
    }

    public boolean isWithdraw() {
        return withdraw;
    }

    public void setWithdraw(boolean withdraw){
        this.withdraw = withdraw;
    }

    public boolean isFromCompanyInfo() {
        return fromCompanyInfo;
    }

    public void setFromCompanyInfo(boolean fromCompanyInfo) {
        this.fromCompanyInfo = fromCompanyInfo;
    }
}
