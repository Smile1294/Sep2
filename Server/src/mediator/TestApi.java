package mediator;

import com.google.gson.Gson;
import model.Company;

import java.io.IOException;

public class TestApi {
    public static void main(String[] args) throws IOException, InterruptedException {
        Company company = new Company("Tesla Inc.","TSLA");
        StockAPI stockAPI = new StockAPI();
        Gson gson = new Gson();

        // deserialize from API request
        String json = stockAPI.getStockIntraDay60Min(Symbol.TESLA);
        StockInfo stockInfo = gson.fromJson(json,StockInfo.class).convert();
        System.out.println(stockInfo.getOpen());
        company.setPrices(stockInfo.getOpen());
        System.out.println(company.toString());


    }
}
