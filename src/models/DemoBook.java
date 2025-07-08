package models;

public class DemoBook extends Book {


  public DemoBook(String ISBN, String title, int yearOfPublication, int numberOfValidYears) {
    super(ISBN, title, yearOfPublication, numberOfValidYears);
  }

  public DemoBook(String title, int yearOfPublication, int numberOfValidYears) {
    super(title, yearOfPublication, numberOfValidYears);
  }

  @Override
  public String toString() {
    return "DemoBook: " + super.toString();
  }

}
