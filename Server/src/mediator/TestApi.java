package mediator;

import com.google.gson.Gson;
import java.io.IOException;

public class TestApi {
    public static void main(String[] args) throws IOException, InterruptedException {
        StockAPI stockAPI = new StockAPI();
        Gson gson = new Gson();

        // deserialize from API request
        String json = stockAPI.getStockIntraDay60Min(Symbol.FACEBOOK);
        StockInfo stockInfo = gson.fromJson(json,StockInfo.class).convert();
        System.out.println(stockInfo);
    }
}
