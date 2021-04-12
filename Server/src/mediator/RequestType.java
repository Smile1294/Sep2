package mediator;

public enum RequestType {
    INTRADAY("TIME_SERIES_INTRADAY"),DAILY("TIME_SERIES_DAILY"),WEEKLY("TIME_SERIES_WEEKLY"),
    MONTHLY("TIME_SERIES_MONTHLY"),;

    private String requestType;

    private RequestType(String requestType){
        this.requestType = requestType;
    }

    public String getRequestType(){
        return requestType;
    }
}
