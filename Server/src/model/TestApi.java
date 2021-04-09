package model;


import com.google.gson.Gson;
import mediator.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class TestApi {
    public static void main(String[] args) throws IOException, InterruptedException {
        StockAPI test = new StockAPI();
        Gson gson = new Gson();

        // get json from 1 api
//        test.testRapidApi();

        // get json information from 2nd api (this is working with stock info)
//        test.testAlphaVantage();

        // deserialize from JsonTest class
        StockInfo stockInfo = gson.fromJson(JsonTest.getJsonRequest(),StockInfo.class);
        System.out.println(stockInfo.toString());

        // deserialize from json file
//        Reader reader = Files.newBufferedReader(Paths.get("query.json"));
//        StockInfo testFileData = gson.fromJson(reader,StockInfo.class);
//        System.out.println(testFileData);
//        reader.close();


        //Testing serializing map
//        Map<String,TradingData> mapTest = new HashMap<>();
//        for (int i = 0;;i++){
//            mapTest.put(""+i,new TradingData((double) i,(double) i,(double) i,(double) i,i));
//            if (i>=12){
//                break;
//            }
//        }
//        System.out.println(gson.toJson(mapTest));
    }
}
