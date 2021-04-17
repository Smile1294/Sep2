package mediator;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class StockInfo {
    @SerializedName("Meta Data")
    private MetaData metaData;
    @SerializedName(value="Time Series (60min)", alternate={"Time Series (Daily)","Weekly Time Series","Monthly Time Series"})
    private Map<String,TradingData> tradingDataMap;

    private ArrayList<TradingData> timeSeries;

    public StockInfo(){
        metaData = null;
        tradingDataMap = null;
        timeSeries = null;
    }

    public StockInfo(MetaData metaData, Map<String,TradingData> tradingDataMap) {
        this.metaData = metaData;
        this.tradingDataMap = tradingDataMap;
        timeSeries = null;
    }

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

    public ArrayList<Double> getOpen() {
        ArrayList<Double> prices = new ArrayList<Double>();
            for (String k : tradingDataMap.keySet()) {
                prices.add(tradingDataMap.get(k).getOpen());
            }
        return prices;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public void setTradingDataMap(Map<String, TradingData> tradingDataMap) {
        this.tradingDataMap = tradingDataMap;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Map<String, TradingData> getTradingDataMap() {
        return tradingDataMap;
    }

    public ArrayList<TradingData> getTimeSeries() {
        return timeSeries;
    }

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
