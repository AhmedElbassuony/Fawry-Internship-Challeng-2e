package test;

import enums.FileType;
import models.*;
import org.junit.Assert;
import org.junit.Test;
import services.BookStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BookStoreTest {
  @Test
  public void addPaperBookTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    Book book1 = new PaperBook("Clean Code", 2011, 20, 200.5, 5);
    bookStore.add(book1);
    expectedBooks.put(book1.getISBN(), book1);
    Book book2 = new PaperBook("How To Program in Java", 2010, 20, 200.5, 5);
    bookStore.add(book2);
    expectedBooks.put(book2.getISBN(), book2);
    Assert.assertEquals(expectedBooks, inventory.getBooks());
  }

  @Test
  public void addExistingPaperBookTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    Book book1 = new PaperBook("Clean Code", 2011, 20, 200.5, 5);
    bookStore.add(book1);
    bookStore.add(book1);
    bookStore.add(book1);
    ((PaperBook) book1).addQuantity(10);
    expectedBooks.put(book1.getISBN(), book1);

    Assert.assertEquals(expectedBooks, inventory.getBooks());
  }

  @Test
  public void addEBookTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    Book book1 = new EBook("Clean Code", 2011, 20, 200.5, FileType.PDF);
    bookStore.add(book1);
    expectedBooks.put(book1.getISBN(), book1);
    Book book2 = new EBook("How To Program in Java", 2010, 20, 200.5, FileType.DOCX);
    bookStore.add(book2);
    expectedBooks.put(book2.getISBN(), book2);
    Assert.assertEquals(expectedBooks, inventory.getBooks());
  }

  @Test
  public void addExistingEBookTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    Book book1 = new EBook("Clean Code", 2011, 20, 200.5, FileType.PDF);
    expectedBooks.put(book1.getISBN(), book1);
    bookStore.add(book1);
    Assert.assertEquals(expectedBooks, inventory.getBooks());
    Assert.assertThrows(IllegalArgumentException.class, () -> bookStore.add(book1));
  }

  @Test
  public void addPaperBookAndEBookTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    Book book1 = new PaperBook("Clean Code", 2011, 20, 200.5, 5);
    bookStore.add(book1);
    expectedBooks.put(book1.getISBN(), book1);
    Book book2 = new EBook("How To Program in Java", 2010, 20, 200.5, FileType.DOCX);
    bookStore.add(book2);
    expectedBooks.put(book2.getISBN(), book2);
    Assert.assertEquals(expectedBooks, inventory.getBooks());
  }

  @Test
  public void removeOutDatedBookTest() {
    int currentYear = LocalDate.now().getYear();
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    HashMap<String, Book> expectedOutDatedBooks = new HashMap<>();
    Book book1 = new PaperBook("Clean Code", currentYear - 9, 20, 200.5, 1);
    Book book2 = new PaperBook("The art of linux", currentYear - 40, 20, 200.5, 5);
    Book book3 = new PaperBook("progit", currentYear - 20, 10, 200.5, 2);
    Book book4 = new EBook("Clean Code", currentYear - 9, 20, 200.5, FileType.PDF);
    Book book5 = new EBook("The art of linux", currentYear - 40, 20, 200.5, FileType.DOCX);
    Book book6 = new EBook("progit", currentYear - 20, 10, 200.5, FileType.PDF);
    expectedBooks.put(book1.getISBN(), book1);
    expectedBooks.put(book4.getISBN(), book4);
    expectedOutDatedBooks.put(book2.getISBN(), book2);
    expectedOutDatedBooks.put(book3.getISBN(), book3);
    expectedOutDatedBooks.put(book5.getISBN(), book5);
    expectedOutDatedBooks.put(book6.getISBN(), book6);
    bookStore.add(book1);
    bookStore.add(book2);
    bookStore.add(book3);
    bookStore.add(book4);
    bookStore.add(book5);
    bookStore.add(book6);
    HashMap<String, Book> outDatedBooks = bookStore.removeOutDatedBooks();

    Assert.assertEquals(expectedOutDatedBooks, outDatedBooks);
    Assert.assertEquals(expectedBooks, inventory.getBooks());
  }

  @Test
  public void buyingPaperBookTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    Book book1 = new PaperBook("Clean Code", 2011, 20, 200.5, 5);
    bookStore.add(book1);
    double expectedPrice = 200.5 * 3;
    double price = bookStore.buy(book1.getISBN(), 3, "Ahmed@gmail.com", "Cairo");
    Assert.assertEquals(expectedPrice, price, 0.0);
    Assert.assertEquals(((PaperBook) book1).getQuantity(), 2);
  }

  @Test
  public void buyingEBookTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    Book book1 = new EBook("Clean Code", 2011, 20, 200.5, FileType.PDF);
    bookStore.add(book1);
    double expectedPrice = 200.5 * 3;
    double price = bookStore.buy(book1.getISBN(), 3, "Ahmed@gmail.com", "Cairo");
    Assert.assertEquals(expectedPrice, price, 0.0);
  }

  @Test
  public void buyingPaperBooksMoreThanInTheStockTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    Book book1 = new PaperBook("Clean Code", 2011, 20, 200.5, 5);
    bookStore.add(book1);
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      bookStore.buy(book1.getISBN(), 10, "Ahmed@gmail.com", "Cairo");
    });
  }

  @Test
  public void invalidBookTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      Book book1 = new PaperBook("Clean Code", 2011, -1, 200.5, 5);
    });
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      Book book1 = new PaperBook("Clean Code", 2011, 20, -23, 5);
    });
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      Book book1 = new PaperBook("Clean Code", 2011, 20, 200.5, -5);
    });
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      Book book1 = new PaperBook("Clean Code", -2011, 20, 200.5, 5);
    });
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      Book book1 = new PaperBook("", 2011, 20, 200.5, 5);
    });
  }

  @Test
  public void invalidBuyingTest() {
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    Book book1 = new PaperBook("Clean Code", 2011, 20, 200.5, 5);
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      bookStore.buy(book1.getISBN(), 3, "Ahmed@gmail.com", "Cairo");
    });
    bookStore.add(book1);
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      bookStore.buy(book1.getISBN(), 3, "Ahmed@gmail.com", "");
    });
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      bookStore.buy(book1.getISBN(), -3, "Ahmed@gmail.com", "Cairo");
    });
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      bookStore.buy(book1.getISBN(), 30, "Ahmed@gmail.com", "Cairo");
    });
    bookStore.buy(book1.getISBN(), 3, "", "Cairo");
    Book book2 = new EBook("Clean Code", 2011, 20, 200.5, FileType.PDF);
    bookStore.add(book2);
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      bookStore.buy(book2.getISBN(), 3, "", "Cairo");
    });
    Book book3 = new DemoBook("Clean Code", 2011, 20);
    bookStore.add(book3);
    Assert.assertThrows(IllegalArgumentException.class, () -> {
      bookStore.buy(book3.getISBN(), 3, "Ahmed@gmail.com", "Cairo");
    });
  }

}
