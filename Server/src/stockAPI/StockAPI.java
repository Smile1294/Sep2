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
    /**
     * gets stock info of company
     * @param symbol symbol of company to be checked
     * @param requestType type of intervals of asked information
     * @return stock info of company as string
     * @throws IOException if an I/O error occurs when sending or recei
     * @throws InterruptedException if the operation is interrupted
     */
    public String getStockInfo(String symbol, RequestType requestType) throws IOException, InterruptedException {
        String interval = "";
        if (requestType.equals(RequestType.INTRADAY)){
            interval = "&interval=60min";
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.alphavantage.co/query?function="+requestType.getRequestType()+"&symbol="+symbol+interval+"&apikey="+APIKey.getKey()))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
