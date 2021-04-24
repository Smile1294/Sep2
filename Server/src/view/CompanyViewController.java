package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import view.chart.BarData;
import view.chart.CandleStickChart;

import javafx.scene.chart.XYChart;
import view.chart.MainApp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class CompanyViewController extends ViewController
{
  @FXML private CategoryAxis time;
  @FXML private NumberAxis price;
  @FXML private CandleStickChart candleChart;
  @FXML private XYChart<String, Number> testChart;
  @FXML private LineChart<String, Number> chart;
  @FXML private Label nameLabel;
  @FXML private Label priceLabel;

  @Override protected void init()
  {
    nameLabel.textProperty().bind(getViewModelFactory().getCompanyViewModel().getNameProperty());
    priceLabel.textProperty().bind(getViewModelFactory().getCompanyViewModel().getPriceProperty().asString());
    candleChart = new CandleStickChart("Test", buildBars());
    testChart = new CandleStickChart("Test", buildBars());

//    getRoot().sceneProperty().get().
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
    getViewModelFactory().getCompanyListViewModel().setFromCompany();
    getViewHandler().openView(View.PLACE_ORDER);
  }


  // IMPORTANT! REMOVE LATER!!!

  public List<BarData> buildBars() {
    double previousClose = 1850;


    final List<BarData> bars = new ArrayList<>();
    GregorianCalendar now = new GregorianCalendar();
    for (int i = 0; i < 26; i++) {
      double open = getNewValue(previousClose);
      double close = getNewValue(open);
      double high = Math.max(open + getRandom(),close);
      double low = Math.min(open - getRandom(),close);
      previousClose = close;

      BarData bar = new BarData((GregorianCalendar) now.clone(), open, high, low, close, 1);
      now.add(Calendar.MINUTE, 5);
      bars.add(bar);
    }
    return bars;
  }


  protected double getNewValue( double previousValue ) {
    int sign;

    if( Math.random() < 0.5 ) {
      sign = -1;
    } else {
      sign = 1;
    }
    return getRandom() * sign + previousValue;
  }

  protected double getRandom() {
    double newValue = 0;
    newValue = Math.random() * 10;
    return newValue;
  }
}
