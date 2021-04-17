package viewmodel;

import model.Model;

import java.io.IOException;

public class ViewModelFactory {
    private PlaceOrderViewModel placeOrderController;
    private CompanyListViewModel companyListViewModel;
    private CompanyViewModel companyViewModel;
    private LoginViewModel loginViewModel;
    private RegisterViewModel registerViewModel;
    private AccountViewModel accountViewModel;
    public ViewModelFactory(Model model) throws IOException {
        UserInformation userInformation = new UserInformation();
        this.placeOrderController = new PlaceOrderViewModel(model);
        this.loginViewModel = new LoginViewModel(model,userInformation);
        this.registerViewModel = new RegisterViewModel(model,userInformation);
        accountViewModel = new AccountViewModel(model,userInformation);
        this.companyListViewModel = new CompanyListViewModel(model);
        this.companyViewModel = new CompanyViewModel();
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

    public CompanyListViewModel getCompanyListViewModel()
    {
        return companyListViewModel;
    }

    public CompanyViewModel getCompanyViewModel()
    {
        return companyViewModel;
    }
}
