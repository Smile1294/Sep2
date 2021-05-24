package model;

import java.io.Serializable;

/**
 * Message represents class which sends threw RMI connection class order and price
 */
public class Message implements Serializable
{
  private Order order;
  private Price price;

  /**
   * Constructor that initializes all instant variables
   * @param order order class
   * @param price price class
   */
  public Message(Order order, Price price){
    this.order = order;
    this.price = price;
  }

  /**
   * gets order class
   * @return order
   */
  public Order getOrder()
  {
    return order;
  }

  /**
   * gets price class
   * @return price
   */
  public Price getPriceObject()
  {
    return price;
  }
}
