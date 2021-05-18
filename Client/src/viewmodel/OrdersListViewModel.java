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

import java.util.UUID;

/**
 * OrderListViewModel is class for functionality of orderlist view
 */
public class OrdersListViewModel implements LocalListener<String, Order> {
    private Model model;
    private ViewState viewState;
    private ObservableList<SimpleOrderViewModel> simpleOrderViewModels;
    private StringProperty Company;
    private DoubleProperty amount;
    private DoubleProperty initAmount;
    private StringProperty Status;
    private DoubleProperty Price;

    /**
     * Constructor that is initialising all the instance variables
     *
     * @param model     model for functionality
     * @param viewState viewState state of the account
     */

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

    /**
     * clears the information and sets it to default
     */
    public void clear() {
        simpleOrderViewModels.removeAll(getSimpleOrderViewModels());
        loadOrders();
    }

    /**
     * CloseOrder closes selected order
     *
     * @param uuid
     */
    public void CloseOrder(UUID uuid) {
        try {
            model.CloseOrder(uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * returns list of simpleorders class
     */

    public ObservableList<SimpleOrderViewModel> getSimpleOrderViewModels() {
        return simpleOrderViewModels;
    }

    /**
     * Loads all orders that user owns
     *
     * @throws Exception
     */
    private void loadOrders() {
        try {
            for (Order o : model.getAllUserOrders(viewState.getUserName().getName())) {
                simpleOrderViewModels.add(new SimpleOrderViewModel(o));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets company
     *
     * @return company
     */
    public StringProperty companyProperty() {
        return Company;
    }

    /**
     * gets amount
     *
     * @return amount
     */
    public DoubleProperty amountProperty() {
        return amount;
    }

    /**
     * gets initial amount
     *
     * @return initamount
     */
    public DoubleProperty initAmountProperty() {
        return initAmount;
    }

    /**
     * gets status
     *
     * @return status
     */
    public StringProperty statusProperty() {
        return Status;
    }

    /**
     * gets price property
     *
     * @return Price
     */
    public DoubleProperty priceProperty() {
        return Price;
    }

    /**
     * Finids matching uuid in list with uuid of order that will be deleted
     *
     * @param uuid
     */
    private void RemoveOrder(UUID uuid) {
        for (SimpleOrderViewModel e : simpleOrderViewModels) {
            if (e.getUuid().equals(uuid)) {
                simpleOrderViewModels.remove(e);
                return;
            }
        }
    }

    /**
     * add order to list
     *
     * @param order
     */
    private void addOrder(Order order) {
        simpleOrderViewModels.add(new SimpleOrderViewModel(order));
    }

    /**
     * if there is change of order from open to close it will update view
     *
     * @param event
     */
    @Override
    public void propertyChange(ObserverEvent<String, Order> event) {
        Platform.runLater(() ->
        {
            try {
                if (event.getPropertyName().equals("ClosingOrder")) {
                    RemoveOrder(UUID.fromString(event.getValue2().getOrderId()));
                    addOrder(event.getValue2());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}
