package model;

/**
 * Stocks class represents list of stocks
 */

import java.io.Serializable;
import java.util.ArrayList;

public class Stocks implements Serializable {
    private ArrayList<Stock> stocks;

    /**
     * Constructor which is initialising arraylist of type stock
     */

    public Stocks() {
        this.stocks = new ArrayList<>();

    }

    /**
     * getting all stocks
     *
     * @return all stocksd
     */

    public ArrayList<Stock> getAllStocks() {
        {
            return new ArrayList<>(stocks);
        }
    }

    /**
     * gets the stock by symbol
     *
     * @param symbol symbol that is being searched
     * @return stock
     */

    public Stock getStockBySymbol(String symbol) {
        try {
            for (Stock stock : stocks) {
                if (symbol.equals(stock.getSymbol())) {
                    return stock;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * gets the stock by symbol
     *
     * @param user username that is beeing serached
     * @return stocks list
     */
    public Stocks getStocksByUser(String user) {
        Stocks list = new Stocks();
        try {
            for (Stock stock : stocks) {
                if (user.equals(stock.getUsername())) {
                    list.addStock(stock);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * adding a stock to the list
     *
     * @param stock that is being added
     */


    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    /**
     * getting the size of the stock
     *
     * @return stock size
     */

    public int getSize() {
        return stocks.size();
    }

    /**
     * removing the stock
     *
     * @param stock stock that is being removed
     */

    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }

    /**
     * getting the stock
     *
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
     *
     * @param i index of the stock
     * @return stock
     */

    public Stock getStock(int i) {
        return stocks.get(i);
    }

    /**
     * toString version of the stocks
     *
     * @return stocks
     */

    @Override
    public String toString() {
        return "Stocks{" +
                "stocks=" + stocks +
                '}';
    }
}
