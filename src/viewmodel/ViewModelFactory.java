package viewmodel;

import model.Model;

public class ViewModelFactory {
    private PlaceOrderViewModel placeOrderController;
    public ViewModelFactory(Model model){
        this.placeOrderController = new PlaceOrderViewModel(model);
    }

    public PlaceOrderViewModel getPlaceOrderController() {
        return placeOrderController;
    }




}
