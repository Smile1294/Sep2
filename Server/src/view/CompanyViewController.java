package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import utility.NumberStringConverter;


public class CompanyViewController extends ViewController
{
  @FXML private Label symbolLabel;
  @FXML private Label nameLabel;
  @FXML private Label priceLabel;
  @FXML private LineChart<String,Double> historyChart;

  @Override protected void init()
  {
    nameLabel.textProperty().bind(getViewModelFactory().getCompanyViewModel().getNameProperty());
    Bindings.bindBidirectional(priceLabel.textProperty(),
            getViewModelFactory().getCompanyViewModel().getPriceProperty(), new NumberStringConverter());
    symbolLabel.textProperty().bind(getViewModelFactory().getCompanyViewModel().getSymbolProperty());
    reset();
  }

  @Override public void reset()
  {
    getViewModelFactory().getCompanyViewModel().clear();
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
