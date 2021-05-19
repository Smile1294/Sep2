package viewmodel;

import model.Model;
import view.View;

import java.io.IOException;

public class ViewModelFactory {
    private PlaceOrderViewModel placeOrderController;
    private CompanyListViewModel companyListViewModel;
    private CompanyViewModel companyViewModel;
    private LoginViewModel loginViewModel;
    private RegisterViewModel registerViewModel;
    private AccountViewModel accountViewModel;
    private TransferViewModel transferViewModel;
    private PortfolioViewModel portfolioViewModel;
    private OrdersListViewModel ordersListViewModel;

    public ViewModelFactory(Model model) throws IOException {
        ViewState viewState = new ViewState();
        this.placeOrderController = new PlaceOrderViewModel(model,viewState);
        this.loginViewModel = new LoginViewModel(model,viewState);
        this.registerViewModel = new RegisterViewModel(model);
        this.accountViewModel = new AccountViewModel(model,viewState);
        this.transferViewModel = new TransferViewModel(model,viewState);
        this.portfolioViewModel = new PortfolioViewModel(model,viewState);
        this.companyListViewModel = new CompanyListViewModel(model,viewState);
        this.companyViewModel = new CompanyViewModel(model,viewState);
        this.ordersListViewModel = new OrdersListViewModel(model,viewState);
    }

    public PlaceOrderViewModel getPlaceOrderController() {
        return placeOrderController;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public RegisterViewModel getRegisterViewModel() {
        return registerViewModel;
    }

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public TransferViewModel getTransferViewModel() {
        return transferViewModel;
    }

    public PortfolioViewModel getPortfolioViewModel(){return  portfolioViewModel;}

    public CompanyListViewModel getCompanyListViewModel()
    {
        return companyListViewModel;
    }

    public CompanyViewModel getCompanyViewModel()
    {
        return companyViewModel;
    }

    public OrdersListViewModel getOrdersListViewModel() { return ordersListViewModel; }
}
