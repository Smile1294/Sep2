package mediator;

public class MetaData {
    private String information;
    private String symbol;
    private String lastRefreshed;
    private String interval;
    private String outputSize;
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
        return "Information: "+information
                + "\nSymbol: " + symbol
                + "\nLast Refreshed: " + lastRefreshed
                + "\nInterval: " + interval
                + "\nOutput Size: " + outputSize
                + "\nTime Zone: " + timeZone;
    }
}
