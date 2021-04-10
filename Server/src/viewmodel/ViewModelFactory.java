package viewmodel;

import model.Model;

public class ViewModelFactory {
    private PlaceOrderViewModel placeOrderController;
    private CompanyListViewModel companyListViewModel;
    private CompanyViewModel companyViewModel;
    public ViewModelFactory(Model model){
        this.placeOrderController = new PlaceOrderViewModel(model);
        this.companyListViewModel = new CompanyListViewModel(model);
        this.companyViewModel = new CompanyViewModel();
    }

    public PlaceOrderViewModel getPlaceOrderController() {
        return placeOrderController;
    }

    public CompanyListViewModel getCompanyListViewModel()
    {
        return companyListViewModel;
    }

    public CompanyViewModel getCompanyViewModel()
    {
        return companyViewModel;
    }
}
