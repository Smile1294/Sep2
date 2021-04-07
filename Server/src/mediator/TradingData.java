package mediator;

public class TradingData {
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private int volume;

    public TradingData(String date, double open, double high, double low, double close, int volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Date: " + date+
                "\nOpen: " + open +
                "\nHigh: " + high +
                "\nLow: " + low +
                "\nClose: " + close +
                "\nVolume: " + volume;
    }
}
