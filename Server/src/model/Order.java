package model;

import mediator.Symbol;

import java.math.BigDecimal;
import java.util.UUID;

public class Order {
    private boolean sell;
    private BigDecimal askingPrice;
    private int amount;
    private int initialAmount;
    private Status status;
    private String username;
    private UUID orderId;
    private String symbol;

    public Order(boolean sell, BigDecimal askingPrice, int initialAmount, String user, Status status, String symbol) {
        this.initialAmount = initialAmount;
        this.amount = initialAmount;
        this.askingPrice = askingPrice;
        this.sell = sell;
        this.username = user;
        this.status = status;
        this.symbol = symbol;
        this.orderId = UUID.randomUUID();
    }

    public String getSymbol() {
        return symbol;
    }

    public int getInitialAmount() {
        return initialAmount;
    }

    public boolean isSell() {
        return sell;
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

    public String getUser() {
        return username;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void close() {
        setStatus(Status.CLOSED);
    }
    public void complete() {
        setStatus(Status.COMPLETED);
    }

    public String getOrderId() {
        return orderId.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Order) {
            Order other = (Order) o;
            return this.orderId.equals(other.orderId);
//            return this.stock.equals(other.stock) && this.sell == other.sell &&
//                    this.askingPrice.equals(other.askingPrice) && this.amount == other.amount &&
//                    this.status.equals(other.status) && this.user.equals(other.user);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sell=" + sell +
                ", askingPrice=" + askingPrice +
                ", amount=" + amount +
                ", initialAmount=" + initialAmount +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", orderId=" + orderId +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
