package model;

import java.util.ArrayList;

public class Company {
    private String name;
    private ArrayList<Double> prices;
    private String Symbol;
    public Company(String name,String symbol)
    {
        this.name = name;
        this.Symbol = symbol;
        this.prices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setPrices(ArrayList<Double> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", prices=" + prices +
                ", Symbol='" + Symbol + '\'' +
                '}';
    }
}
