package models;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Book {
  private final String ISBN;
  private String title;
  private int yearOfPublication;
  private int numberOfValidYears;

  public Book(String ISBN, String title, int yearOfPublication, int numberOfValidYears) {
    this.ISBN = ISBN;
    setTitle(title);
    setYearOfPublication(yearOfPublication);
    setNumberOfValidYears(numberOfValidYears);
  }

  public Book(String title, int yearOfPublication, int numberOfValidYears) {
    this(UUID.randomUUID().toString(), title, yearOfPublication, numberOfValidYears);
  }

  public String getTitle() {
    return title;
  }

  public int getYearOfPublication() {
    return yearOfPublication;
  }

  public void setTitle(String title) {
    if (title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }
    this.title = title;
  }

  public void setYearOfPublication(int yearOfPublication) {
    if (yearOfPublication < 1 || yearOfPublication > LocalDate.now().getYear()) {
      throw new IllegalArgumentException("Year of publication must be a valid year");
    }
    this.yearOfPublication = yearOfPublication;
  }

  public String getISBN() {
    return ISBN;
  }

  public int getNumberOfValidYears() {
    return numberOfValidYears;
  }

  public void setNumberOfValidYears(int numberOfValidYears) {
    if (numberOfValidYears < 0) {
      throw new IllegalArgumentException("Number of valid years cannot be negative");
    }
    this.numberOfValidYears = numberOfValidYears;
  }

  public boolean outOfDate() {
    return LocalDate.now().getYear() - yearOfPublication > numberOfValidYears;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return book.ISBN.equals(ISBN);
  }

  @Override
  public int hashCode() {
    return ISBN.hashCode();
  }

  @Override
  public String toString() {
    return String.format("Book[ISBN=%s, title=%s, year=%d, #Valid Years=%d]", ISBN, title, yearOfPublication, numberOfValidYears);
  }
}
