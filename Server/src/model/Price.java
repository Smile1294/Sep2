package model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Price class represents price information of company
 */

public class Price implements Serializable
{
  private Timestamp timestamp;
  private String symbol;
  private Double price;

  /**
   * Constructor that initializes all instant variables
   * @param timestamp time of price taken
   * @param symbol symbol of company to witch price goes
   * @param price price of company
   */

  public Price(Timestamp timestamp, String symbol, Double price){
    this.timestamp = timestamp;
    this.symbol = symbol;
    this.price = price;
  }

  /**
   * setts of the price taken
   * @param timestamp time when was price saved
   */

  public void setTimestamp(Timestamp timestamp)
  {
    this.timestamp = timestamp;
  }

  /**
   * setts price
   * @param price price variable of Double
   */
  public void setPrice(Double price)
  {
    this.price = price;
  }

  /**
   * gets time of price taken
   * @return time stamp
   */
  public Timestamp getTimestamp()
  {
    return timestamp;
  }

  /**
   * gets a symbol of company
   * @return symbol of company
   */
  public String getSymbol()
  {
    return symbol;
  }

  /**
   * gets price of company
   * @return price as double variable of company
   */
  public Double getPrice()
  {
    return price;
  }

  /**
   * toString version of price
   * @return price
   */
  @Override public String toString()
  {
    return "Price{" + "timestamp=" + timestamp + ", symbol='" + symbol + '\''
        + ", price=" + price + '}' + '\n';
  }
}
