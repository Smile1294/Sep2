package viewmodel;

import javafx.beans.property.*;
import model.Order;

import java.util.UUID;


/**
 * SimpleCompanyViewModel is class for functionality of orders
 */

public class SimpleOrderViewModel {
    private StringProperty Company;
    private DoubleProperty amount;
    private IntegerProperty initAmount;
    private StringProperty Status;
    private DoubleProperty Price;
    private UUID uuid;
    private BooleanProperty selling;


    public SimpleOrderViewModel(Order order) {
        Company = new SimpleStringProperty(order.getSymbol());
        amount = new SimpleDoubleProperty(order.getAmount());
        initAmount = new SimpleIntegerProperty(order.getInitialAmount());
        Status = new SimpleStringProperty(order.getStatus().getStatus());
        Price = new SimpleDoubleProperty(order.getAskingPrice());
        uuid = UUID.fromString(order.getOrderId());
        selling = new SimpleBooleanProperty(order.isSell());
    }


    public StringProperty companyProperty() {
        return Company;
    }

    public StringProperty isSelling() {
        if (selling.get()) {
            return new SimpleStringProperty("Selling");
        }
        return new SimpleStringProperty("Buying");

    }

    public StringProperty amountProperty() {
        return new SimpleStringProperty(String.valueOf(amount.get()));
    }


    public StringProperty initAmountProperty() {
        return new SimpleStringProperty(String.valueOf(initAmount.get()));
    }

    public UUID getUuid() {
        return uuid;
    }

    public StringProperty statusProperty() {
        return Status;
    }

    public StringProperty priceProperty() {
        return new SimpleStringProperty(String.valueOf(Price.get()));
    }
}
