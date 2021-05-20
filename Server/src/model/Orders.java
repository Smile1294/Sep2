package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Orders represents a list of orders
 */


public class Orders implements Runnable, LocalSubject<String, Order>, Serializable {
    private PropertyChangeHandler<String, Order> property;
    private List<Order> orders;


    /**
     * Constructor initialising arraylist of type order
     */

    public Orders() {
        orders = new ArrayList<>();
        this.property = new PropertyChangeHandler<>(this, true);
    }

    /**
     * adding an order
     *
     * @param order order that is being added
     */

    public void AddOrder(Order order) {
        if (order.isSell()) {
            orders.add(order);
        } else if (!order.isSell()) {
            orders.add(order);
        }
    }

    /**
     * returning an array list of orders
     */
    public ArrayList<Order> getOrders() {
        ArrayList<Order> userOrders = new ArrayList<>();
        userOrders.addAll(orders);
        return userOrders;
    }

    /**
     * returning an arraylist of user orders by name
     *
     * @param user
     */
    public ArrayList<Order> getUserOrders(String user) {
        ArrayList<Order> userOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getUser().equals(user)) {
                userOrders.add(o);
            }
        }
        return userOrders;
    }

    /**
     * returning an arraylist of completed orders
     */
    public ArrayList<Order> getCompletedOrders() {
        ArrayList<Order> CompletedOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getStatus().equals(Status.COMPLETED)) {
                CompletedOrders.add(o);
            }
        }
        return CompletedOrders;
    }

    /**
     * closing an order
     *
     * @param order order that is being closed
     */
    public void closeOrder(Order order) {
        for (Order o : orders) {
            if (o.equals(order)) {
                o.close();
                return;
            }
        }
    }

    public int getAmount2(String user, Order order) {
        int i = 0;

        for (Order o : getCompletedOrders()) {
            if (user.equals(o.getUser()) && o.getSymbol().equals(order.getSymbol())) {
                i = i + o.getAmount();
            }
        }
        return i;
    }

    /**
     * getting the order on sale
     *
     * @return order
     */
    public ArrayList<Order> getForSale() {
        ArrayList<Order> forSale = new ArrayList<>();
        for (Order o : orders) {
            if (o.isSell() && o.getStatus().equals(Status.OPEN)) {
                forSale.add(o);
            }
        }
        return forSale;
    }

    /**
     * Checking if order exists in list
     *
     * @return boolean
     */
    public Boolean getOrderbyId(Order order) {
        for (Order o : orders) {
            if (o.getOrderId().equals(order.getOrderId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finding order by Id
     *
     * @return boolean
     */
    public Order getOrderbyID(String uuid) {
        for (Order o : orders) {
            if (o.getOrderId().equals(uuid)) {
                return o;
            }
        }
        return null;
    }


    /**
     * getting order to buy
     *
     * @return order
     */
    public ArrayList<Order> getToBuy() {
        ArrayList<Order> toBuy = new ArrayList<>();
        for (Order o : orders) {
            if (!o.isSell() && o.getStatus().equals(Status.OPEN)) {
                toBuy.add(o);
            }
        }
        return toBuy;
    }

    /**
     * adding order
     *
     * @param order order that is being added
     */
    private void add(Order order) {
        orders.add(order);
    }

    /**
     * getting the order by the user
     *
     * @param user user that is getting checked
     * @return order
     */
    public Orders getOrderByUser(User user) {
        Orders byUser = new Orders();
        for (Order o : orders) {
            if (o.getUser().equals(user.getUserName().getName())) {
                byUser.add(o);
            }
        }
        return byUser;
    }

    /**
     * This methods will count how much has user invested in specific stock taking the the price and amount of stocks from order multiplying them and then adding them to stock
     * @param user
     * @param stock
     * @return Double how much has user invested in specific stock
     */

    public Double getboughtPriceInStock(User user, Stock stock) {
        double d = 0;
        for (int i = 0; i < getOrderByUser(user).orders.size(); i++) {
            if (getOrderByUser(user).orders.get(i).getStatus().equals(Status.COMPLETED) && !getOrderByUser(user).orders.get(i).isSell()) {
                if (stock.getSymbol().equals(orders.get(i).getSymbol())) {
                    d = d + orders.get(i).getAmount() * getOrderByUser(user).orders.get(i).getAskingPrice();
                }
            }
            if (getOrderByUser(user).orders.get(i).getStatus().equals(Status.OPEN) || getOrderByUser(user).orders.get(i).getStatus().equals(Status.COMPLETED) && getOrderByUser(user).orders.get(i).isSell()) {
                if (stock.getSymbol().equals(orders.get(i).getSymbol())) {
                    d = d - orders.get(i).getAmount() * getOrderByUser(user).orders.get(i).getAskingPrice();
                }
            }
        }
        return d;
    }

    /**
     * toString version of the Orders
     *
     * @return orders
     */
    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                '}';
    }

    /**
     * Thread that is run everytime a new order is added will loop trought all orders that are for sale and are that are to buy and will execute every matching sell/buy order
     * update theyr value and fire them to model manager where they will be saved to database
     */
    @Override
    public void run() {
        for (Order o : getForSale()) {
            for (Order b : getToBuy()) {
                if (!o.getUser().equals(b.getUser())) {
                    if (b.getStatus().equals(Status.OPEN) && o.getStatus().equals(Status.OPEN)) {
                        if (o.getAskingPrice() <= b.getAskingPrice() && o.getSymbol().equals(b.getSymbol())) {
                            if (o.getAmount() > b.getAmount()) {
                                o.setAmount(o.getAmount() - b.getAmount());
                                b.complete();
                                property.firePropertyChange("OrderCompleted", b.getOrderId(), b);

                            }
                            if (o.getAmount() == b.getAmount()) {
                                o.setAmount(0);
                                b.complete();
                                o.complete();
                                property.firePropertyChange("OrderCompleted", o.getOrderId(), o);
                                property.firePropertyChange("OrderCompleted", b.getOrderId(), b);

                            }
                            if (o.getAmount() < b.getAmount()) {
                                b.setAmount(b.getAmount() - o.getAmount());
                                o.complete();
                                property.firePropertyChange("OrderCompleted", o.getOrderId(), o);


                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean addListener(GeneralListener listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);

    }

    @Override
    public boolean removeListener(GeneralListener listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);
    }
}

