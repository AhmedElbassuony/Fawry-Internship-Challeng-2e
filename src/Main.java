import models.Book;
import models.Inventory;
import models.PaperBook;
import services.BookStore;

import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to my Book Store");
    Inventory inventory = new Inventory();
    BookStore bookStore = new BookStore(inventory);
    HashMap<String, Book> expectedBooks = new HashMap<>();
    Book book1 = new PaperBook("Clean Code", 2011, 20, 200.5, 5);
    bookStore.add(book1);
    double price = bookStore.buy(book1.getISBN(), 3, "Ahmed@gmail.com", "Cairo");
    System.out.println("The price is: " + price);
  }
}
