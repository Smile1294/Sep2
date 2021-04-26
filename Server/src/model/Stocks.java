package model;

import mediator.Symbol;

import java.util.ArrayList;

public class Stocks {
    private ArrayList<Stock> stocks;


    public Stocks() {
        this.stocks = new ArrayList<>();
    }
    public ArrayList<Stock> getAllStocks()
    {
        {
            ArrayList<Stock> localList = new ArrayList<>();
            for (Stock stock : stocks) {
                localList.add(stock);
            }
            return localList;
        }
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public int getSize() {
        return stocks.size();
    }

    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    public Stock getStock(Stock stock){
        for (Stock s: stocks){
            if (s.equals(stock)){
                return stock;
            }
        }
        return null;
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
