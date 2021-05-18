package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Order;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.rmi.RemoteException;
import java.util.UUID;

public class OrdersListViewModel implements LocalListener<String, Order> {
    private Model model;
    private ViewState viewState;
    private ObservableList<SimpleOrderViewModel> simpleOrderViewModels;
    private StringProperty Company;
    private DoubleProperty amount;
    private DoubleProperty initAmount;
    private StringProperty Status;
    private DoubleProperty Price;

    public OrdersListViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        model.addListener(this);
        Company = new SimpleStringProperty();
        amount = new SimpleDoubleProperty();
        initAmount = new SimpleDoubleProperty();
        Status = new SimpleStringProperty();
        Price = new SimpleDoubleProperty();
        simpleOrderViewModels = FXCollections.observableArrayList();
    }

    public void clear() {
        simpleOrderViewModels.removeAll(getSimpleOrderViewModels());
        loadOrders();
    }

    public void CloseOrder(UUID uuid) {
        try {
            model.CloseOrder(uuid);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<SimpleOrderViewModel> getSimpleOrderViewModels() {
        return simpleOrderViewModels;
    }

    private void loadOrders() {
        try {
            for (Order o : model.getAllUserOrders(viewState.getUserName().getName())) {
                simpleOrderViewModels.add(new SimpleOrderViewModel(o));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StringProperty companyProperty() {
        return Company;
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public DoubleProperty initAmountProperty() {
        return initAmount;
    }

    public StringProperty statusProperty() {
        return Status;
    }

    public DoubleProperty priceProperty() {
        return Price;
    }

    private void RemoveOrder(UUID uuid) {
        for (SimpleOrderViewModel e : simpleOrderViewModels) {
            if (e.getUuid().equals(uuid)) {
                simpleOrderViewModels.remove(e);
                return;
            }
        }
    }

    private void addOrder(Order order) {
        simpleOrderViewModels.add(new SimpleOrderViewModel(order));
    }


    @Override
    public void propertyChange(ObserverEvent<String, Order> event) {
        Platform.runLater(() ->
        {
            try {
                System.out.println(event.getValue2().toString());
                if (event.getPropertyName().toString().equals("ClosingOrder")) {
                    System.out.println("Closing");
                    RemoveOrder(UUID.fromString(event.getValue2().getOrderId()));
                    addOrder(event.getValue2());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}
