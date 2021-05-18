package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * Orders represents a list of orders
 */


public class Orders implements Runnable, LocalSubject {
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

    @Override
    public void run() {
        for (Order o : getForSale()) {
            for (Order b : getToBuy()) {
                if (!o.getUser().equals(b.getUser())) {
                    if (o.getAskingPrice() <= b.getAskingPrice() && o.getSymbol().equals(b.getSymbol())) {
                        if (o.getAmount() > b.getAmount()) {
                            o.setAmount(o.getAmount() - b.getAmount());
                            b.complete();
                            property.firePropertyChange("OrderCompleted", b.getOrderId(), b);
                            property.firePropertyChange("OrderCompleted", o.getOrderId(), o);

                        }
                        if (o.getAmount() == b.getAmount()) {
                            b.complete();
                            o.complete();
                            o.setAmount(0);
                            property.firePropertyChange("OrderCompleted", o.getOrderId(), o);
                            property.firePropertyChange("OrderCompleted", b.getOrderId(), b);

                        }
                        if (o.getAmount() < b.getAmount()) {
                            o.complete();
                            b.setAmount(b.getAmount() - o.getAmount());
                            property.firePropertyChange("OrderCompleted", o.getOrderId(), o);
                            property.firePropertyChange("OrderCompleted", b.getOrderId(), b);


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

