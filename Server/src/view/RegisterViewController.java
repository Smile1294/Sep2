package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import viewmodel.RegisterViewModel;

import java.io.IOException;

public class RegisterViewController extends ViewController
{
  @FXML private TextField usernameField, emailField, emailConfirm;
  @FXML private PasswordField passwordField, passwordConfirm;
  @FXML private Label errorLabel;
  private RegisterViewModel viewModel;

  @Override protected void init()
  {
    viewModel = getViewModelFactory().getRegisterViewModel();
    usernameField.textProperty().bindBidirectional(viewModel.getUsername());
    passwordField.textProperty().bindBidirectional(viewModel.getPassword());
    passwordConfirm.textProperty().bindBidirectional(viewModel.getPasswordConfirm());
    emailField.textProperty().bindBidirectional(viewModel.getEmail());
    emailConfirm.textProperty().bindBidirectional(viewModel.getEmailConfirm());
    errorLabel.textProperty().bind(viewModel.getError());
  }

  @Override public void reset(){
    getViewModelFactory().getRegisterViewModel().clear();
  }

  @FXML
  private void onBack(ActionEvent actionEvent)
  {
    getViewHandler().openView(View.LOGIN);
  }

  @FXML
  private void onRegister(ActionEvent actionEvent) throws IOException
  {

    if (viewModel.register())
    {
      getViewHandler().openView(View.LOGIN);
    }
  }
}
