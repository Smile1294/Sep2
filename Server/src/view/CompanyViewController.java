package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;


public class CompanyViewController extends ViewController
{
  @FXML private Label nameLabel;
  @FXML private Label priceLabel;
  @FXML private LineChart<String,Double> historyChart;

  @Override protected void init()
  {
    nameLabel.textProperty().bind(getViewModelFactory().getCompanyViewModel().getNameProperty());
    priceLabel.textProperty().bind(getViewModelFactory().getCompanyViewModel().getPriceProperty().asString());
  }

  @Override public void reset()
  {
    getViewModelFactory().getCompanyViewModel().load();
  }

  public void onBack(ActionEvent actionEvent)
  {

    getViewHandler().openView(View.COMPANY_LIST);
  }

  public void onOrder(ActionEvent actionEvent)
  {
    getViewHandler().openView(View.PLACE_ORDER);
  }
}
