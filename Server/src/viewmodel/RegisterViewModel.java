package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.io.IOException;

public class RegisterViewModel
{
  private Model model;
  private StringProperty username, password, passwordConfirm, email, emailConfirm, error;
  private UserInformation userInformation;

  public RegisterViewModel(Model model, UserInformation userInformation){
    this.model = model;
    this.userInformation = userInformation;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    passwordConfirm = new SimpleStringProperty();
    email = new SimpleStringProperty();
    emailConfirm = new SimpleStringProperty();
    error = new SimpleStringProperty();
  }

  public void clear(){
    username.setValue(null);
    password.setValue(null);
    error.setValue(null);
  }

  public boolean register() throws IOException
  {

    boolean result = false;
    // redo this
    // try { model.register(new user(username, password, passwordConfirm, email, emailConfirm));}
    // catch (exception e) {error.setValue(e.getMessage());}
    if (username != null && password != null) // this should be handled in username class
    {
      try {
        result = model.registerUser(username.get(), password.get());
        userInformation.setUser(username.get()); //userInfo (future viewState?) has to be updated, wait not in register.
      } catch (Exception e){
        error.setValue(e.getMessage());
      }

    }
    else {
      error.setValue("there is missing name or password");
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

  public StringProperty getEmail()
  {
    return email;
  }

  public StringProperty getEmailConfirm(){
    return emailConfirm;
  }
}
