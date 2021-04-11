package mediator;


import com.google.gson.Gson;

import java.io.IOException;


public class TestApi {
    public static void main(String[] args) throws IOException, InterruptedException {
        StockAPI test = new StockAPI();
        Gson gson = new Gson();

        // get json from 1 api
//        test.testRapidApi();

        // get json information from 2nd api (this is working with stock info)
//        test.testAlphaVantage();

        // deserialize from JsonTest class
//        StockInfo stockInfo = gson.fromJson(JsonTest.getJsonRequest(),StockInfo.class);
//        System.out.println(stockInfo.toString());

        // deserialize from API request
        String json = test.getStockIntraDay60Min(Symbol.APPLE);
//        System.out.println(json);
        StockInfo stockInfo2 = gson.fromJson(json,StockInfo.class);
        System.out.println(stockInfo2.toString());

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
