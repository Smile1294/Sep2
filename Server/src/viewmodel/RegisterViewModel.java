package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.*;

import java.io.IOException;

public class RegisterViewModel
{
  private Model model;
  private StringProperty username, password, passwordConfirm, error, email, emailConfirm;

  public RegisterViewModel(Model model){
    this.model = model;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    passwordConfirm = new SimpleStringProperty();
    error = new SimpleStringProperty();
    email = new SimpleStringProperty();
    emailConfirm = new SimpleStringProperty();
  }

  public void clear(){
    username.setValue(null);
    password.setValue(null);
    passwordConfirm.setValue(null);
    error.setValue(null);
    email.setValue(null);
    emailConfirm.setValue(null);
  }

  public boolean register() {
    boolean result = false;
    try {
      result = model.registerUser(
              new User(new UserName(username.get()),
                      new Password(password.get()), new Password(passwordConfirm.get()),
                      new Email(email.get()),new Email(emailConfirm.get())));
      clear();
    } catch (Exception e) {
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

  public StringProperty getError(){
    return error;
  }

  public StringProperty getPasswordConfirm()
  {
    return passwordConfirm;
  }

  public StringProperty getEmail(){
    return email;
  }

  public StringProperty getEmailConfirm(){
    return emailConfirm;
  }
}
