package model;


import com.google.gson.Gson;
import mediator.*;

import java.io.IOException;
import java.util.ArrayList;


public class TestApi {
    public static void main(String[] args) throws IOException, InterruptedException {
        StockAPI test = new StockAPI();
        Gson gson = new Gson();

//        test.testRapidApi();
//        test.testAlphaVantage();
//        StockInfo stockInfo = gson.fromJson(JsonTest.getJsonRequest(),StockInfo.class);
//        System.out.println(stockInfo.toString());

        MetaData metaData = new MetaData("Intraday (5min) open, high, low, close prices and volume",
                "IBM","2021-04-06 20:00:00","5min","Compact","US/Eastern");
        ArrayList<TradingData> series = new ArrayList<>();
        series.add(new TradingData("2021-04-06 20:00:00",134.4100,134.4100,134.4100,134.4100,185));
        series.add(new TradingData("2021-04-06 19:25:00",134.4100,134.4100,134.4100,134.4100,123));
        StockInfo testtest = new StockInfo(metaData,series);
//        System.out.println(testtest.toString());
        System.out.println(gson.toJson(testtest));
        System.out.println(JsonTest.getJsonRequest());
        MetaData testdata = gson.fromJson(JsonTest.getJsonRequest(),MetaData.class);
        System.out.println(testdata.toString());

    }
}
