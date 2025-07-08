package services;

import models.Book;
import models.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookStore {
  private final Inventory inventory;

  public BookStore(Inventory inventory) {
    if (inventory == null) {
      throw new NullPointerException("inventory is null");
    }
    this.inventory = inventory;
  }

  public void add(Book book) {
    inventory.addBook(book);
  }

  public HashMap<String, Book> removeOutDatedBooks() {
    return inventory.removeOutDatedBooks();
  }

  public double buy(String ISBN, int quantity, String email, String address) {
    return inventory.sellBook(ISBN, quantity, email, address);
  }
}
