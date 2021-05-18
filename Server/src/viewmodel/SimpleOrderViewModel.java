package viewmodel;

import javafx.beans.property.*;
import model.*;
import java.util.UUID;


/**
 * SimpleOrderViewModel is used to make list of simpleorders that will be displayed in OrdersList view
 */

public class SimpleOrderViewModel {
    private StringProperty Company;
    private DoubleProperty amount;
    private IntegerProperty initAmount;
    private StringProperty Status;
    private DoubleProperty Price;
    private UUID uuid;
    private BooleanProperty selling;

    /**
     * Constructor for SimplerOrderViewModel
     * @param order
     */
    public SimpleOrderViewModel(Order order) {
        Company = new SimpleStringProperty(order.getSymbol());
        amount = new SimpleDoubleProperty(order.getAmount());
        initAmount = new SimpleIntegerProperty(order.getInitialAmount());
        Status = new SimpleStringProperty(order.getStatus().getStatus());
        Price = new SimpleDoubleProperty(order.getAskingPrice());
        uuid = UUID.fromString(order.getOrderId());
        selling = new SimpleBooleanProperty(order.isSell());
    }

    /**
     * gets company
     * @return Company
     */

    public StringProperty companyProperty() {
        return Company;
    }

    /**
     * gets boolean of order if its for sale
     * @return boolean
     */
    public StringProperty isSelling() {
        if (selling.get()) {
            return new SimpleStringProperty("Selling");
        }
        return new SimpleStringProperty("Buying");

    }

    /**
     * gets amount of order
     * @return amount
     */
    public StringProperty amountProperty() {
        return new SimpleStringProperty(String.valueOf(amount.get()));
    }

    /**
     * gets initial amount
     * @return gets init amount
     */
    public StringProperty initAmountProperty() {
        return new SimpleStringProperty(String.valueOf(initAmount.get()));
    }

    /**
     * gets UUID of order
     * @return uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * gets Status
     * @return status
     */
    public StringProperty statusProperty() {
        return Status;
    }

    /**
     * gets Price
     * @return price
     */
    public StringProperty priceProperty() {
        return new SimpleStringProperty(String.valueOf(Price.get()));
    }
}
