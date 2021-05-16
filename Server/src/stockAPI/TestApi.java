package stockAPI;

import com.google.gson.Gson;


import java.io.IOException;


public class TestApi {
    public static void main(String[] args) throws IOException, InterruptedException {
        StockAPI stockAPI = new StockAPI();
        Gson gson = new Gson();


        // deserialize from API request
        String json = stockAPI.getStockInfo(Symbol.FACEBOOK,RequestType.INTRADAY);
        StockInfo stockInfo = gson.fromJson(json,StockInfo.class).convert();
        System.out.println(stockInfo);

        Thread.sleep(2000);

        json = stockAPI.getStockInfo(Symbol.IBM,RequestType.DAILY);
        stockInfo = gson.fromJson(json,StockInfo.class).convert();
        System.out.println(stockInfo);

        Thread.sleep(2000);

        json = stockAPI.getStockInfo(Symbol.TESLA,RequestType.WEEKLY);
        stockInfo = gson.fromJson(json,StockInfo.class).convert();
        System.out.println(stockInfo);

        Thread.sleep(2000);

        json = stockAPI.getStockInfo(Symbol.MICROSOFT,RequestType.MONTHLY);
        stockInfo = gson.fromJson(json,StockInfo.class).convert();
        System.out.println(stockInfo);
    }
}