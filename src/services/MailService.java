package services;

import interfaces.SendingService;
import models.Book;

public class MailService implements SendingService {
  public void send(Book book, String sendingAddress) {
    System.out.println("Sending The EBook" + book.getTitle() + " to " + sendingAddress);
  }
}
