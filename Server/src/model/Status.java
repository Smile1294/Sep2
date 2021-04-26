package model;

public enum Status {
    OPEN("Open"),CLOSED("Closed"),COMPLETED("Completed");

    private String status;

    private Status(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
