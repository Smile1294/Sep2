package mediator;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class StockInfo {
    @SerializedName("Meta Data")
    private MetaData metaData;
    @SerializedName("Time Series (60min)")
    private Map<String,TradingData> timeSeries;

//    @SerializedName("Time Series (5min)")
//    private Map<Date,TradingData> timeSeries;

    public StockInfo(MetaData metaData, Map<String,TradingData> timeSeries) {
        this.metaData = metaData;
        this.timeSeries = timeSeries;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Map<String,TradingData> getTimeSeries() {
        return timeSeries;
    }

    @Override public String toString(){
        StringBuilder sb = new StringBuilder(metaData.toString());
        for (String k : timeSeries.keySet()){
            sb.append("\n").append(k).append("\n").append(timeSeries.get(k));
        }
        return sb.toString();
    }
}
