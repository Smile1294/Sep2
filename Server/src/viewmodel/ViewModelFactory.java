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
    private TransferCashViewModel transferCashViewModel;
    private PortfolioViewModel portfolioViewModel;

    public ViewModelFactory(Model model) throws IOException {
        UserInformation userInformation = new UserInformation();
        TransferState transferState = new TransferState();
        this.placeOrderController = new PlaceOrderViewModel(model);
        this.loginViewModel = new LoginViewModel(model,userInformation);
        this.registerViewModel = new RegisterViewModel(model,userInformation);
        this.accountViewModel = new AccountViewModel(model,userInformation,transferState);
        this.transferCashViewModel = new TransferCashViewModel(model,userInformation,transferState);
        this.portfolioViewModel = new PortfolioViewModel(model);
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

    public TransferCashViewModel getTransferCashViewModel() {
        return transferCashViewModel;
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
}
