package model;

import java.util.ArrayList;

public class StocksUserOwns {
    private ArrayList<Stock> stocks;
    private String owner;


    public StocksUserOwns(String owner) {
        this.stocks = new ArrayList<>();
        this.owner = owner;
    }

    public ArrayList<Stock> getAllStocks() {
        {
            ArrayList<Stock> localList = new ArrayList<>();
            for (int i = 0; i < stocks.size(); i++) {
                localList.add(stocks.get(i));
            }
            return localList;
        }
    }


    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public Stock getSpecificStock(Stock stock) {
        for (Stock s : getAllStocks()) {
            if (s.getName().equals(stock.getName())) {
                return s;
            }
        }
        return null;

    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public int getSize() {
        return stocks.size();
    }

    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    public Stock getStock(int i) {
        return stocks.get(i);
    }

    @Override
    public String toString() {
        return "Stocks{" +
                "stocks=" + stocks +
                '}';
    }
}
