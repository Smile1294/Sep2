package model;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Order class represents a order of the user
 */

public class Order
{
    private Stock stock;
    private boolean sell;
    private BigDecimal askingPrice;
    private int amount;
    private Status status;
    private User user;
    private UUID orderId;
    private Company company;

    /**
     * Constructor that initialises all the instance variables
     * @param company company that hold the stock
     * @param sell is it for sell stock
     * @param askingPrice asking price for a stock
     * @param amount amount of stock
     * @param user which user
     * @param status status of the stock
     */

    public Order(Company company, boolean sell, BigDecimal askingPrice, int amount, User user, Status status){
        this.amount = amount;
        this.askingPrice = askingPrice;
        this.sell = sell;
        this.company = company;
        this.user=user;
        this.status=status;
        this.orderId = UUID.randomUUID();
    }

    /**
     * if its for sale
     * @return sell
     */

    public boolean isSell() {
        return sell;
    }

    /**
     * gets the stock
     * @return stock
     */

    public Stock getStock() {
        return stock;
    }

    /**
     * gets asking price for a stock
     * @return asking price
     */

    public Double getAskingPrice() {
        return askingPrice.doubleValue();
    }

    /**
     * gets the amount of the stock
     * @return amount
     */

    public int getAmount() {
        return amount;
    }

    /**
     * gets status
     * @return status
     */

    public Status getStatus() {
        return status;
    }

    /**
     * gets a user
     * @return user
     */

    public User getUser() {
        return user;
    }

    /**
     * sets amount of stock
     * @param amount amount of the stock
     */

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * sets status of order
     * @param status status of the order
     */

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * sets the status of order to closed
     */

    public void close(){
        setStatus(Status.CLOSED);
    }

    /**
     * gets the order id
     * @return order id
     */

    public String getOrderId() {
        return orderId.toString();
    }

    /**
     *  comparing an object to the order
     * @param o the object that is getting compared with
     * @return returns order id
     */

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

    /**
     * toString version of the Order
     * @return order
     */

    @Override
    public String toString() {
        return "Order{" +
                "stock=" + stock +
                ", sell=" + sell +
                ", askingPrice=" + askingPrice +
                ", amount=" + amount +
                ", status=" + status +
                ", user=" + user +
                ", orderId=" + orderId +
                '}';
    }
}
