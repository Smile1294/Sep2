package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.*;

import java.io.IOException;

/**
 * RegisterViewModel is class for functionality of register view
 */

public class RegisterViewModel {
    private Model model;
    private StringProperty username, password, passwordConfirm, error, email, emailConfirm;

    /**
     * Constructor that is initialising all the instance variables
     * @param model model for functionality
     **/
    public RegisterViewModel(Model model) {
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        passwordConfirm = new SimpleStringProperty();
        error = new SimpleStringProperty();
        email = new SimpleStringProperty();
        emailConfirm = new SimpleStringProperty();
    }

    /**
     * clears the information and sets it to default
     */

    public void clear() {
        username.setValue(null);
        password.setValue(null);
        passwordConfirm.setValue(null);
        error.setValue(null);
        email.setValue(null);
        emailConfirm.setValue(null);
    }

    /**
     * adding register to model
     * @return true if user information is correct
     */

    public boolean register() {
        boolean result = false;
        try {
            result = model.registerUser(
                    new User(new UserName(username.get()),
                            new Password(password.get()), new Password(passwordConfirm.get()),
                            new Email(email.get()), new Email(emailConfirm.get())));
            clear();
        } catch (Exception e) {
            error.setValue(e.getMessage());
        }
        return result;
    }

    /**
     * gets username
     * @return username
     */

    public StringProperty getUsername() {
        return username;
    }

    /**
     * gets password
     * @return password
     */

    public StringProperty getPassword() {
        return password;
    }

    /**
     * gets error
     * @return error
     */

    public StringProperty getError() {
        return error;
    }

    /**
     * gets password to confirm
     * @return password
     */

    public StringProperty getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * gets email
     * @return email
     */

    public StringProperty getEmail() {
        return email;
    }

    /**
     * gets email to confirm
     * @return email
     */

    public StringProperty getEmailConfirm() {
        return emailConfirm;
    }
}
