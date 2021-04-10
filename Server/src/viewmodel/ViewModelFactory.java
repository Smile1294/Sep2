package viewmodel;

import model.Model;

public class ViewModelFactory {
    private PlaceOrderViewModel placeOrderController;
    private CompanyListViewModel companyListViewModel;
    public ViewModelFactory(Model model){
        this.placeOrderController = new PlaceOrderViewModel(model);
        this.companyListViewModel = new CompanyListViewModel(model);
    }

    public PlaceOrderViewModel getPlaceOrderController() {
        return placeOrderController;
    }

    public CompanyListViewModel getCompanyListViewModel()
    {
        return companyListViewModel;
    }
}
