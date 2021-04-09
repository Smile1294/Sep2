package mediator;

import com.google.gson.annotations.SerializedName;

public class TradingData {
//    private String date;
    @SerializedName("1. open")
    private double open;
    @SerializedName("2. high")
    private double high;
    @SerializedName("3. low")
    private double low;
    @SerializedName("4. close")
    private double close;
    @SerializedName("5. volume")
    private int volume;

    public TradingData( double open, double high, double low, double close, int volume) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
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
        return "Open: " + open +
                "\nHigh: " + high +
                "\nLow: " + low +
                "\nClose: " + close +
                "\nVolume: " + volume;
    }
}
