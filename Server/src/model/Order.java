package model;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {
    private Stock stock;
    private boolean sell;
    private BigDecimal askingPrice;
    private int amount;
    private Status status;
    private User user;
    private UUID orderId;

    public Order(Stock stock, boolean sell, BigDecimal askingPrice, int amount, User user, Status status){
        this.amount = amount;
        this.askingPrice = askingPrice;
        this.sell = sell;
        this.stock = stock;
        this.user=user;
        this.status=status;

        this.orderId = UUID.randomUUID();
    }

    public boolean isSell() {
        return sell;
    }

    public Stock getStock() {
        return stock;
    }

    public Double getAskingPrice() {
        return askingPrice.doubleValue();
    }

    public int getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void close(){
        setStatus(Status.CLOSED);
    }

    public String getOrderId() {
        return orderId.toString();
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (o == this){
            return true;
        }
        if (o instanceof Order){
            Order other = (Order) o;
            return this.orderId.equals(other.orderId);
//            return this.stock.equals(other.stock) && this.sell == other.sell &&
//                    this.askingPrice.equals(other.askingPrice) && this.amount == other.amount &&
//                    this.status.equals(other.status) && this.user.equals(other.user);
        }
        return false;
    }
}
