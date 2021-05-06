package model;

import java.util.ArrayList;
import java.util.List;

public class Orders implements Runnable {
    private List<Order> orders;

    public Orders() {
        orders = new ArrayList<>();
    }

    public void AddOrder(Order order) {
        if (order.isSell()) {
            orders.add(order);
        } else if (!order.isSell()) {
            orders.add(order);

        }
    }

    public ArrayList<Order> getUserOrders(String user) {
        ArrayList<Order> userOrders = new ArrayList<>();
        for (Order o : orders) {
            if (o.getUser().equals(user)) {
                userOrders.add(o);
            }
        }
        return userOrders;
    }

    public void closeOrder(Order order) {
        for (Order o : orders) {
            if (o.equals(order)) {
                o.close();
                return;
            }
        }
    }

    public ArrayList<Order> getForSale() {
        ArrayList<Order> forSale = new ArrayList<>();
        for (Order o : orders) {
            if (o.isSell() && o.getStatus().equals(Status.OPEN)) {
                forSale.add(o);
            }
        }
        return forSale;
    }

    public ArrayList<Order> getToBuy() {
        ArrayList<Order> toBuy = new ArrayList<>();
        for (Order o : orders) {
            if (!o.isSell() && o.getStatus().equals(Status.OPEN)) {
                toBuy.add(o);
            }
        }
        return toBuy;
    }

    private void add(Order order) {
        orders.add(order);
    }

    public Orders getOrderByUser(User user) {
        Orders byUser = new Orders();
        for (Order o : orders) {
            if (o.getUser().equals(user.getUserName().getName())) {
                byUser.add(o);
            }
        }
        return byUser;
    }

    public int getCompeltedUserOwnedStock(String symbol, String name) {
        int i = 0;
        for (Order o : getUserOrders(name)) {
            if (o.getSymbol().equals(symbol) && !o.isSell() && o.getStatus().equals(Status.COMPLETED)) {
                i = i + o.getAmount();
            }
        }
        return i;
    }

    public Double getboughtPrice(User user) {
        double d = 0;
        for (int i = 0; i < getOrderByUser(user).orders.size(); i++) {
            if (getOrderByUser(user).orders.get(i).getStatus() == Status.COMPLETED && !getOrderByUser(user).orders.get(i).isSell()) {
                d = d + getOrderByUser(user).orders.get(i).getAskingPrice();
            }
        }
        return d;
    }

    public Double getboughtPriceInStock(User user, Stock stock) {
        double d = 0;
        for (int i = 0; i < getOrderByUser(user).orders.size(); i++) {
            if (getOrderByUser(user).orders.get(i).getStatus() == Status.COMPLETED && !getOrderByUser(user).orders.get(i).isSell())
                if (stock.getSymbol().equals(orders.get(i).getSymbol())) {
                    d = d + orders.get(i).getAmount() * getOrderByUser(user).orders.get(i).getAskingPrice();
                }
        }
        return d;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                '}';
    }

    @Override
    public void run() {
        while (true) {
            for (Order o : getForSale()) {
                for (Order b : getToBuy()) {
                    if (!o.getUser().equals(b.getUser())) {
                        if (o.getAskingPrice() <= b.getAskingPrice() && o.getSymbol().equals(b.getSymbol())) {
                            System.out.println(o.getAskingPrice() + "  >   " + b.getAskingPrice());
                            if (o.getAmount() > b.getAmount()) {
                                o.setAmount(o.getAmount() - b.getAmount());
                                b.complete();
                            }
                            if (o.getAmount() == b.getAmount()) {
                                o.setAmount(0);
                                b.complete();
                                o.complete();
                            }
                            if (o.getAmount() < b.getAmount()) {
                                o.complete();
                                b.setAmount(b.getAmount() - o.getAmount());
                            }
                        }
                    }
                }
            }
            try {
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
