package mediator;

import com.google.gson.annotations.SerializedName;


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

    public MetaData(String information, String symbol, String lastRefreshed, String interval, String outputSize, String timeZone) {
        this.information = information;
        this.symbol = symbol;
        this.lastRefreshed = lastRefreshed;
        this.interval = interval;
        this.outputSize = outputSize;
        this.timeZone = timeZone;
    }

    public String getInformation() {
        return information;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public String getInterval() {
        return interval;
    }

    public String getOutputSize() {
        return outputSize;
    }

    public String getTimeZone() {
        return timeZone;
    }

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
