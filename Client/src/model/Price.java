package model;

import java.sql.Timestamp;

public class Price
{
  private Timestamp timestamp;
  private String symbol;
  private Double price;

  public Price(Timestamp timestamp, String symbol, Double price){
    this.timestamp = timestamp;
    this.symbol = symbol;
    this.price = price;
  }

  public void setTimestamp(Timestamp timestamp)
  {
    this.timestamp = timestamp;
  }

  public void setPrice(Double price)
  {
    this.price = price;
  }

  public Timestamp getTimestamp()
  {
    return timestamp;
  }

  public String getSymbol()
  {
    return symbol;
  }

  public Double getPrice()
  {
    return price;
  }

  @Override public String toString()
  {
    return "Price{" + "timestamp=" + timestamp + ", symbol='" + symbol + '\''
        + ", price=" + price + '}' + '\n';
  }
}
