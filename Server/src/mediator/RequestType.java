package mediator;

/**
 * Request type is a class representing request type from the api
 */

public enum RequestType {
    INTRADAY("TIME_SERIES_INTRADAY"),DAILY("TIME_SERIES_DAILY"),WEEKLY("TIME_SERIES_WEEKLY"),
    MONTHLY("TIME_SERIES_MONTHLY"),;

    private String requestType;

    /**
     *  Constructor that is initialising instance variable
     * @param requestType the type that is requested
     */

    private RequestType(String requestType){
        this.requestType = requestType;
    }

    /**
     * getting request type
     * @return request type
     */

    public String getRequestType(){
        return requestType;
    }
}
