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
    private TransferViewModel transferViewModel;
    private PortfolioViewModel portfolioViewModel;

    public ViewModelFactory(Model model) throws IOException {
        UserInformation userInformation = new UserInformation();
        TransferState transferState = new TransferState();
        this.placeOrderController = new PlaceOrderViewModel(model,transferState);
        this.loginViewModel = new LoginViewModel(model,userInformation);
        this.registerViewModel = new RegisterViewModel(model,userInformation);
        this.accountViewModel = new AccountViewModel(model,userInformation,transferState);
        this.transferViewModel = new TransferViewModel(model,userInformation,transferState);
        this.portfolioViewModel = new PortfolioViewModel(model);
        this.companyListViewModel = new CompanyListViewModel(model,transferState);
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
}
