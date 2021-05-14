package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Password;
import model.User;
import model.UserName;

/**
 * LoginViewModel is class for functionality of login view
 */

public class LoginViewModel {
    private Model model;
    private StringProperty username, password, error;
    private ViewState viewState;

    /**
     * Constructor that is initialising all the instance variables
     * @param model model for functionality
     * @param viewState viewState state of the account
     */

    public LoginViewModel(Model model, ViewState viewState){
        this.model = model;
        this.viewState = viewState;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        error = new SimpleStringProperty();
    }

    /**
     * clears the information and sets it to default
     */

    public void clear(){
        username.setValue(null);
        password.setValue(null);
        error.setValue(null);
    }

    /**
     * adding login to model
     * @return true if user information is correct
     */

    public boolean logIn(){
        boolean result = false;
        try{
            User user = new User(new UserName(username.get()), new Password(password.get()));
            result = model.login(user);
            viewState.setUserName(user.getUserName());
        }
        catch (Exception e){
            error.setValue(e.getMessage());
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * gets username
     * @return username
     */

    public StringProperty getUsername()
    {
        return username;
    }

    /**
     * gets password
     * @return password
     */

    public StringProperty getPassword()
    {
        return password;
    }

    /**
     * gets error
     * @return error
     */

    public StringProperty getError(){return error;}
}
