package models;

import interfaces.Sellable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Inventory {
  HashMap<String, Book> books;

  public Inventory() {
    books = new HashMap<>();
  }

  public HashMap<String, Book> getBooks() {
    return books;
  }

  public void addBook(Book book) {
    if (book == null) {
      throw new IllegalArgumentException("Book cannot be null");
    }
    if (books.containsKey(book.getISBN())) {
      if (book instanceof EBook) {
        throw new IllegalArgumentException("Book already exists");
      }
      if (book instanceof PaperBook && books.get(book.getISBN()) instanceof PaperBook) {
        PaperBook existing = (PaperBook) books.get(book.getISBN());
        PaperBook incoming = (PaperBook) book;
        existing.addQuantity(incoming.getQuantity());
        return;
      }
    }
    books.put(book.getISBN(), book);
  }

  public HashMap<String, Book> removeOutDatedBooks() {
    HashMap<String, Book> outdatedBooks = new HashMap<>();
    Iterator<Book> iterator = books.values().iterator();
    while (iterator.hasNext()) {
      Book book = iterator.next();
      if (book.outOfDate()) {
        outdatedBooks.put(book.getISBN(),book);
        iterator.remove();
      }
    }
    return outdatedBooks;
  }

  public double sellBook(String ISBN, int quantity, String email, String address) {
    Book book = books.get(ISBN);
    validForSellable(book, quantity, email, address);
    return ((Sellable) book).sell(quantity, email, address);
  }

  public void validForSellable(Book book, int quantity, String email, String address) {
    if (book == null) {
      throw new IllegalArgumentException("Book does not exist");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity cannot be Zero Or negative");
    }
    if (book instanceof PaperBook && (address.isEmpty())) {
      throw new IllegalArgumentException("With Paper book address cannot be empty");
    }
    if (book instanceof EBook && (email.isEmpty())) {
      throw new IllegalArgumentException("With EBook email cannot be empty");
    }
    if (!(book instanceof Sellable)) {
      throw new IllegalArgumentException("Book is not a Sellable");
    }
  }
}
