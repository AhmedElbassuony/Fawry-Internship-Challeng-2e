package models;

import interfaces.Sellable;
import interfaces.SendingService;
import services.ShippingService;

import java.util.UUID;

public class PaperBook extends Book implements Sellable {
  private double price;
  private int quantity;

  public PaperBook(String ISBN, String title, int yearOfPublication, int numberOfValidYears, double price, int quantity) {
    super(ISBN, title, yearOfPublication, numberOfValidYears);
    setPrice(price);
    setQuantity(quantity);
  }

  public PaperBook(String title, int yearOfPublication, int numberOfValidYears, double price, int quantity) {
    this(UUID.randomUUID().toString(), title, yearOfPublication, numberOfValidYears, price, quantity);
  }

  @Override
  public void setPrice(double price) {
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }
    this.price = price;
  }

  @Override
  public double getPrice() {
    return price;
  }

  public void setQuantity(int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity cannot be Zero Or negative");
    }
    this.quantity = quantity;
  }

  public void addQuantity(int quantity) {
    if(quantity <= 0) {
      throw new IllegalArgumentException("Quantity cannot be Zero Or negative");
    }
    this.quantity += quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  @Override
  public double sell(int quantity, String email, String address) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity cannot be Zero Or negative");
    }
    if (quantity > this.quantity) {
      throw new IllegalArgumentException("We do not have enough quantity");
    }
    this.quantity -= quantity;

    SendingService sendingService = new ShippingService();
    sendingService.send(this, address);

    return quantity * price;
  }
}
