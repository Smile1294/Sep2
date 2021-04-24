package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Password;
import model.User;
import model.UserName;

import java.io.IOException;

public class RegisterViewModel
{
  private Model model;
  private StringProperty username, password, passwordConfirm, error;

  public RegisterViewModel(Model model){
    this.model = model;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
    passwordConfirm = new SimpleStringProperty();
    error = new SimpleStringProperty();
  }

  public void clear(){
    username.setValue(null);
    password.setValue(null);
    passwordConfirm.setValue(null);
    error.setValue(null);
  }

  public boolean register() throws IOException
  {
    boolean result = false;
    try {
      result = model.registerUser(new User(new UserName(username.get()), new Password(password.get()), new Password(passwordConfirm.get())));
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

}
