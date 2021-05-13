package model;

/**
 * Stocks class represents list of stocks
 */

import java.util.ArrayList;

public class Stocks {
    private ArrayList<Stock> stocks;

    /**
     * Constructor which is initialising arraylist of type stock
     */

    public Stocks() {
        this.stocks = new ArrayList<>();
    }

    /**
     * getting all stocks
     * @return all stocks
     */

    public ArrayList<Stock> getAllStocks() {
        {
            ArrayList<Stock> localList = new ArrayList<>();
            for (Stock stock : stocks) {
                localList.add(stock);
            }
            return localList;
        }
    }

    /**
     * gets the stock by symbol
     * @param symbol symbol that is being searched
     * @return stock
     */

    public Stock getStockBySymbol(String symbol) {
        for (Stock stock : stocks) {
            if (symbol.equals(stock.getSymbol())) {
                return stock;
            }
        }
        return null;
    }

    /**
     * adding a stock to the list
     * @param stock that is being added
     */


    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    /**
     * getting the size of the stock
     * @return stock size
     */

    public int getSize() {
        return stocks.size();
    }

    /**
     * removing the stock
     * @param stock stock that is being removed
     */

    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    /**
     * getting the stock
     * @param stock stock that is wanted
     * @return stock
     */

    public Stock getStock(Stock stock) {
        for (Stock s : stocks) {
            if (s.equals(stock)) {
                return stock;
            }
        }
        return null;
    }



    /**
     * getting the stock by index
     * @param i index of the stock
     * @return stock
     */

    public Stock getStock(int i) {
        return stocks.get(i);
    }

    /**
     * toString version of the stocks
     * @return stocks
     */

    @Override
    public String toString() {
        return "Stocks{" +
                "stocks=" + stocks +
                '}';
    }
}
