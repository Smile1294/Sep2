package viewmodel;

public class TransferState {
    private boolean withdraw;

    public TransferState(){
        withdraw = true;
    }

    public boolean isWithdraw() {
        return withdraw;
    }

    public void setWithdraw(boolean withdraw){
        this.withdraw = withdraw;
    }
}
