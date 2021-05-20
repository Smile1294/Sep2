package model;

import java.io.Serializable;

public class Message implements Serializable
{
  private Order order;
  private Price price;

  public Message(Order order, Price price){
    this.order = order;
    this.price = price;
  }

  public Order getOrder()
  {
    return order;
  }

  public Price getPriceObject()
  {
    return price;
  }
}
