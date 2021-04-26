package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Password;
import model.User;
import model.UserName;

public class LoginViewModel {
    private Model model;
    private StringProperty username, password, error;
    private ViewState viewState;

    public LoginViewModel(Model model, ViewState viewState){
        this.model = model;
        this.viewState = viewState;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        error = new SimpleStringProperty();
    }

    public void clear(){
        username.setValue(null);
        password.setValue(null);
        error.setValue(null);
    }

    public boolean logIn(){
        boolean result = false;
        try{
            User user = new User(new UserName(username.get()), new Password(password.get()));
            result = model.login(user);
            viewState.setUserName(user.getUserName());
        }
        catch (Exception e){
            error.setValue(e.getMessage());
        }
        return result;
    }

    public StringProperty getUsername()
    {
        return username;
    }

    public StringProperty getPassword()
    {
        return password;
    }

    public StringProperty getError(){return error;}
}
