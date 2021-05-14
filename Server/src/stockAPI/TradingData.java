package stockAPI;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;

/**
 * TradingData class represents trading data for a stock
 */

public class TradingData {
    @SerializedName("1. open")
    private double open;
    @SerializedName("2. high")
    private double high;
    @SerializedName("3. low")
    private double low;
    @SerializedName("4. close")
    private double close;
    @SerializedName("5. volume")
    private long volume;

    private ZonedDateTime dateTime;

    /**
     * Constructor that is initialising all the instance variables
     * @param open opening price of the stock
     * @param high highest price of the stock
     * @param low lowest price of the stock
     * @param close closing price of the stock
     * @param volume volume of the stock that is available
     */

    public TradingData( double open, double high, double low, double close, long volume) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        dateTime = null;
    }

    /**
     * setting date
     * @param date date that is being set up
     * @return trading data
     */

    public TradingData setDate(ZonedDateTime date) {
        this.dateTime = date;
        return this;
    }

    /**
     * getting opening price of the stock
     * @return opening price
     */

    public double getOpen() {
        return open;
    }

    /**
     * getting highest price of the stock
     * @return highest price
     */

    public double getHigh() {
        return high;
    }

    /**
     * getting the lowest price of the stock
     * @return lowest price
     */

    public double getLow() {
        return low;
    }

    /**
     * getting the closing price of the stock
     * @return closing price
     */

    public double getClose() {
        return close;
    }

    /**
     * getting the volume of the stock
     * @return volume of the stock
     */

    public long getVolume() {
        return volume;
    }

    /**
     * getting time
     * @return time
     */

    public ZonedDateTime getDate() {
        return dateTime;
    }

    /**
     * toString version of the Trading data
     * @return trading data
     */

    @Override
    public String toString() {
        return (dateTime == null ? "":"Date: "+dateTime+"\n")+
                "Open: " + open +
                "\nHigh: " + high +
                "\nLow: " + low +
                "\nClose: " + close +
                "\nVolume: " + volume;
    }
}
