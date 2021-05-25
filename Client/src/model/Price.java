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
  private Double open;
  private Double low;
  private Double high;
  private Double close;
  private Long volume;


  public Price(Timestamp timestamp, String symbol,Double open,Double low,Double high, Double close, Long volume){
    this.timestamp = timestamp;
    this.symbol = symbol;
    this.open = open;
    this.low = low;
    this.high = high;
    this.close = close;
    this.volume = volume;
  }

  /**
   * setts of the price taken
   * @param timestamp time when was price saved
   */
  public void setTimestamp(Timestamp timestamp)
  {
    this.timestamp = timestamp;
  }


  public void setPrice(Double close)
  {
    this.close = close;
  }

  public void set(Price price){
    this.timestamp = price.getTimestamp();
    this.symbol = price.getSymbol();
    this.open = price.getOpen();
    this.low = price.getLow();
    this.high = price.getHigh();
    this.close = price.getPrice();
    this.volume = price.getVolume();
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


  public Double getOpen()
  {
    return open;
  }

  public Double getLow()
  {
    return low;
  }

  public Double getHigh()
  {
    return high;
  }

  public Double getPrice()
  {
    return close;
  }

  public Long getVolume()
  {
    return volume;
  }

  @Override public String toString()
  {
    return "Price{" + "timestamp=" + timestamp + ", symbol='" + symbol + '\''
        + ", open=" + open + ", low=" + low + ", high=" + high + ", close="
        + close + ", volume=" + volume + '}';
  }
}
