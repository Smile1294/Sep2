package mediator;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class StockInfo {
    @SerializedName("Meta Data")
    private MetaData metaData;
    @SerializedName("Time Series (60min)")
    private Map<String, TradingData> tradingDataMap;

    private ArrayList<TradingData> timeSeries;

    public StockInfo(MetaData metaData, Map<String, TradingData> tradingDataMap) {
        this.metaData = metaData;
        this.tradingDataMap = tradingDataMap;
        timeSeries = null;
    }

    public StockInfo convert() {
        timeSeries = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        for (String k : tradingDataMap.keySet()) {
            timeSeries.add(tradingDataMap.get(k).setDate(ZonedDateTime.parse(k + " " + metaData.getTimeZone(), formatter)));
        }
        return this;
    }

    public ArrayList<Double> getOpen() {
        ArrayList<Double> prices = new ArrayList<Double>();
            for (String k : tradingDataMap.keySet()) {
                prices.add(tradingDataMap.get(k).getOpen());
            }
        return prices;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public ArrayList<TradingData> getTimeSeries() {
        return timeSeries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(metaData.toString());
        if (timeSeries == null) {
            for (String k : tradingDataMap.keySet()) {
                sb.append("\n").append(k).append("\n").append(tradingDataMap.get(k));
            }
        } else {
            for (TradingData td : timeSeries) {
                sb.append("\n").append(td);
            }
        }
        return sb.toString();
    }
}
