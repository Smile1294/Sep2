package model;


import java.io.IOException;
import java.util.Date;

public class TestApi {
    public static void main(String[] args) throws IOException, InterruptedException {
        StockAPI test = new StockAPI();

//        test.testRapidApi();
        test.testAlphaVantage();

    }
}
