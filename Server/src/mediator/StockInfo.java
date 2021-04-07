package mediator;

import java.util.ArrayList;

public class StockInfo {
    private MetaData metaData;
    private ArrayList<TradingData> timeSeries;

    public StockInfo(MetaData metaData, ArrayList<TradingData> timeSeries) {
        this.metaData = metaData;
        this.timeSeries = timeSeries;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public ArrayList<TradingData> getTimeSeries() {
        return timeSeries;
    }

    @Override public String toString(){
        StringBuilder sb = new StringBuilder(metaData.toString());
        for (TradingData t : timeSeries){
            sb.append("\n").append(t.toString());
        }
        return sb.toString();
    }
}
