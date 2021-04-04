package model;

public class Orders {
    private Stocks forSale;
    private Stocks toBuy;

    public Orders() {
        forSale = new Stocks("Market");
        toBuy = new Stocks("Market");
    }

    public Stocks getForSale() {
        return forSale;
    }

    public Stock AddOrderToBuy(Stock stock) {
        /*    int i;
        Stock stock1;
        for (i = 0; i < this.forSale.getSize(); i++) {
            if (forSale.getStock(i).getPrice() <= stock.getPrice()) {
                stock1 = forSale.getStock(i);
                forSale.removeStock(forSale.getStock(i));
                return stock1;
            }
            else {
                toBuy.addStock(stock);
                return null;
            }

        }
        This adds functioanality for program to handle AddingOrdersToBuy


        */
        toBuy.addStock(stock);
        return null;
    }

    public Stock AddOrderToSell(Stock stock) {
       /*  int i;
        Stock stock1;
        for(i = 0;i < this.toBuy.getSize();i++) {
            if (toBuy.getStock(i).getPrice() <= stock.getPrice()) {
                stock1 = toBuy.getStock(i);
                toBuy.removeStock(toBuy.getStock(i));
                return stock1;
            }
            else {
                forSale.addStock(stock);
                return null;
            }
        }

          This adds functioanality for program to handle AddingOrderstoSell


        */
        forSale.addStock(stock);
        return null;
    }

    public Stocks getToBuy() {
        return toBuy;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "forSale=" + forSale +
                ", toBuy=" + toBuy +
                '}';
    }
}
