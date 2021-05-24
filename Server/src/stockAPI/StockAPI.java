package stockAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * StockAPI class is requesting stock information from api
 */

public class StockAPI {
    public String getStockInfo(String ticker, RequestType requestType) throws IOException, InterruptedException {
        String interval = "";
        if (requestType.equals(RequestType.INTRADAY)){
            interval = "&interval=60min";
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.alphavantage.co/query?function="+requestType.getRequestType()+"&symbol="+ticker+interval+"&apikey="+APIKey.getKey()))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
