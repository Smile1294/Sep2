package viewmodel;

import model.Model;

import java.io.IOException;

public class ViewModelFactory {
    private PlaceOrderViewModel placeOrderController;
    private LoginViewModel loginViewModel;
    private RegisterViewModel registerViewModel;
    public ViewModelFactory(Model model) throws IOException {
        UserInformation userInformation = new UserInformation();
        this.placeOrderController = new PlaceOrderViewModel(model);
        this.loginViewModel = new LoginViewModel(model,userInformation);
        this.registerViewModel = new RegisterViewModel(model,userInformation);
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




}
