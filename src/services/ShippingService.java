package services;

import interfaces.SendingService;
import models.Book;

public class ShippingService implements SendingService {
  public void send(Book book, String sendingAddress) {
    System.out.println("Sending The Paper Book" + book.getTitle() + " to " + sendingAddress);
  }
}
