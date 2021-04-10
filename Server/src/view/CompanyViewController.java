package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CompanyViewController extends ViewController
{
  @FXML public Label nameLabel;
  @FXML public Label priceLabel;

  @Override protected void init()
  {
    nameLabel.textProperty().bind(getViewModelFactory().getCompanyViewModel().getNameProperty());
    priceLabel.textProperty().bind(getViewModelFactory().getCompanyViewModel().getPriceProperty().asString());
  }

  @Override public void reset()
  {
    getViewModelFactory().getCompanyViewModel().load();
  }

  public void backOnAction(ActionEvent actionEvent)
  {
    getViewHandler().openView(View.CompanyList);
  }
}
