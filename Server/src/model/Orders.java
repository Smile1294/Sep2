package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Orders represents a list of orders
 */

public class Orders {
    private List<Order> orders;

    /**
     * Constructor initialising arraylist of type order
     */

    public Orders() {
        orders = new ArrayList<>();
    }

    /**
     * adding an order
     * @param order order that is being added
     */

    public void AddOrder(Order order) {
        if (order.isSell() && order.getUser().UserOwnStock(order.getStock())) {
            orders.add(order);
        }
        else if(!order.isSell())
        {
            if(order.getUser().getBalance()>order.getAskingPrice())
            {
                orders.add(order);
            }
        }
    }

    /**
     * closing an order
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

    /**
     * getting the order on sale
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
     * getting the order by the user
     * @param user user that is getting checked
     * @return order
     */

    public ArrayList<Order> getOrderByUser(User user) {
        ArrayList<Order> byUser = new ArrayList<>();
        for (Order o : orders) {
            if (o.getUser().equals(user)) {
                byUser.add(o);
            }
        }
        return byUser;
    }

    /**
     * toString version of the Orders
     * @return orders
     */

    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                '}';
    }
}
