package stockAPI;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

/**
 * StockInfo class is representing stock information
 */

public class StockInfo {
    @SerializedName("Meta Data")
    private MetaData metaData;
    @SerializedName(value="Time Series (60min)", alternate={"Time Series (Daily)","Weekly Time Series","Monthly Time Series"})
    private Map<String,TradingData> tradingDataMap;

    private ArrayList<TradingData> timeSeries;

    /**
     * Constructor that is setting the instance variables to null
     */

    public StockInfo(){
        metaData = null;
        tradingDataMap = null;
        timeSeries = null;
    }

    /**
     * Constructor that is initialising all the instance variables
     * @param metaData data from stock
     * @param tradingDataMap trading data
     */

    public StockInfo(MetaData metaData, Map<String,TradingData> tradingDataMap) {
        this.metaData = metaData;
        this.tradingDataMap = tradingDataMap;
        timeSeries = null;
    }

    /**
     * converting time zone
     * @return stock information
     */

    public StockInfo convert(){
        timeSeries = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        String timeZone = metaData.getTimeZone();
        if (metaData.getTimeZone()==null){
            timeZone = metaData.getOutputSize();
        }
        if (metaData.getOutputSize()==null) {
            timeZone = metaData.getInterval();
        }
        if (metaData.getInterval()==null){
            timeZone = "US/Eastern";
        }

        for (String k:tradingDataMap.keySet()){
            if (metaData.getTimeZone()==null){
                System.out.println();
                timeSeries.add(tradingDataMap.get(k).setDate(ZonedDateTime.parse(k+" 20:00:00 "+timeZone,formatter)));
            }else {
                timeSeries.add(tradingDataMap.get(k).setDate(ZonedDateTime.parse(k+" "+timeZone,formatter)));
            }
        }
        return this;
    }

    /**
     * getting the open price
     * @return open price
     */

    public ArrayList<Double> getOpen() {
        ArrayList<Double> prices = new ArrayList<Double>();
            for (String k : tradingDataMap.keySet()) {
                prices.add(tradingDataMap.get(k).getOpen());
            }
        return prices;
    }

    /**
     * setting data
     * @param metaData data that is being set
     */

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    /**
     * setting trading data
     * @param tradingDataMap data that is being set
     */

    public void setTradingDataMap(Map<String, TradingData> tradingDataMap) {
        this.tradingDataMap = tradingDataMap;
    }

    /**
     * getting data
     * @return data
     */

    public MetaData getMetaData() {
        return metaData;
    }

    /**
     * getting trading data
     * @return trading data
     */

    public Map<String, TradingData> getTradingDataMap() {
        return tradingDataMap;
    }

    /**
     * getting date
     * @return date
     */

    public ArrayList<TradingData> getTimeSeries() {
        return timeSeries;
    }

    /**
     * toString version of stock information
     * @return stock information
     */

    @Override public String toString(){
        StringBuilder sb = new StringBuilder(metaData.toString());
        if (timeSeries == null) {
            for (String k : tradingDataMap.keySet()) {
                sb.append("\n").append(k).append("\n").append(tradingDataMap.get(k));
            }
        }else {
            for (TradingData td : timeSeries) {
                sb.append("\n").append(td);
            }
        }
        return sb.toString();
    }
}
