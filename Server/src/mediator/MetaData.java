package mediator;

import com.google.gson.annotations.SerializedName;

/**
 * MetaData class is data that is send by stock api
 */

public class MetaData {
    @SerializedName("1. Information")
    private String information;
    @SerializedName("2. Symbol")
    private String symbol;
    @SerializedName("3. Last Refreshed")
    private String lastRefreshed;
    @SerializedName(value="4. Interval", alternate={"4. Output Size","4. Time Zone",})
    private String interval;
    @SerializedName(value = "5. Output Size", alternate = {"5. Time Zone"})
    private String outputSize;
    @SerializedName("6. Time Zone")
    private String timeZone;

    /**
     *  Constructor setting all the instance variables
     * @param information stock information
     * @param symbol company symbol
     * @param lastRefreshed when its last refreshed
     * @param interval interval of stock amount and when if happened
     * @param outputSize output size of stock amount
     * @param timeZone time zone
     */

    public MetaData(String information, String symbol, String lastRefreshed, String interval, String outputSize, String timeZone) {
        this.information = information;
        this.symbol = symbol;
        this.lastRefreshed = lastRefreshed;
        this.interval = interval;
        this.outputSize = outputSize;
        this.timeZone = timeZone;
    }

    /**
     * getting the stock information
     * @return information
     */

    public String getInformation() {
        return information;
    }

    /**
     * getting the symbols
     * @return symbol
     */

    public String getSymbol() {
        return symbol;
    }

    /**
     * getting when its last time refreshed
     * @return last refreshed time
     */

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    /**
     * getting the interval
     * @return interval
     */

    public String getInterval() {
        return interval;
    }

    /**
     * getting the output size
     * @return output size
     */

    public String getOutputSize() {
        return outputSize;
    }

    /**
     * getting time zone
     * @return time zone
     */

    public String getTimeZone() {
        return timeZone;
    }

    /**
     * toString version of stocks data
     * @return stocks data
     */

    @Override public String toString(){
        String stub = "\nInterval: " + interval
                + "\nOutput Size: " + outputSize
                + "\nTime Zone: " + timeZone;
        if (timeZone == null){
            stub = "\nOutput Size: " + interval
                    + "\nTime Zone: " + outputSize;
            if (outputSize==null){
                stub = "\nTime Zone: " + interval;
            }
        }
        return "Information: "+information
                + "\nSymbol: " + symbol
                + "\nLast Refreshed: " + lastRefreshed + stub;

    }
}
