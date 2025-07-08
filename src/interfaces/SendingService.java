package interfaces;

import models.Book;

public interface SendingService  {
  public void send(Book book, String sendingAddress);
}
