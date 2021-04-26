package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orders {
    private List<Order> orders;

    public Orders() {
        orders = new ArrayList<>();
    }

    public void AddOrder(Order order) {
        orders.add(order);
    }

    public void closeOrder(Order order){
        for (Order o: orders){
            if (o.equals(order)){
                o.close();
                return;
            }
        }
    }

    public ArrayList<Order> getForSale() {
        ArrayList<Order> forSale = new ArrayList<>();
        for (Order o : orders){
            if (o.isSell() && o.getStatus().equals(Status.OPEN)){
                forSale.add(o);
            }
        }
        return forSale;
    }

    public ArrayList<Order> getToBuy() {
        ArrayList<Order> toBuy = new ArrayList<>();
        for (Order o : orders){
            if (!o.isSell() && o.getStatus().equals(Status.OPEN)){
                toBuy.add(o);
            }
        }
        return toBuy;
    }



    public ArrayList<Order> getOrderByUser(User user){
        ArrayList<Order> byUser = new ArrayList<>();
        for (Order o : orders){
            if (o.getUser().equals(user)){
                byUser.add(o);
            }
        }
        return byUser;
    }






}
