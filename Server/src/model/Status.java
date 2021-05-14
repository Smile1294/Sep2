package model;

/**
 * Status class is representing statuses of the order
 */

public enum Status {
    OPEN("Open"),CLOSED("Closed"),COMPLETED("Completed");

    private String status;

    /**
     * constructor setting the instance variable
     * @param status status of the stock
     */

    private Status(String status){
        this.status = status;
    }

    /**
     * getting the status
     * @return status
     */

    public String getStatus() {
        return status;
    }
}
