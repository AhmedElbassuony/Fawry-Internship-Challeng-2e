package models;

import enums.FileType;
import interfaces.Sellable;
import interfaces.SendingService;
import services.MailService;

import java.util.UUID;

public class EBook extends Book implements Sellable {
  private FileType fileType;
  private double price;

  public EBook(String ISBN, String title, int yearOfPublication, int numberOfValidYears, double price, FileType fileType) {
    super(ISBN, title, yearOfPublication, numberOfValidYears);
    setPrice(price);
    setFileType(fileType);
  }

  public EBook(String title, int yearOfPublication, int numberOfValidYears, double price, FileType fileType) {
    super(title, yearOfPublication, numberOfValidYears);
    setPrice(price);
    setFileType(fileType);
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public void setPrice(double price) {
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }
    this.price = price;
  }

  public FileType getFileType() {
    return fileType;
  }

  public void setFileType(FileType fileType) {
    if (fileType == null) {
      throw new IllegalArgumentException("File type cannot be null");
    }
    this.fileType = fileType;
  }

  @Override
  public double sell(int quantity, String email, String address) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity cannot be Zero Or negative");
    }

    SendingService sendingService = new MailService();
    sendingService.send(this, email);

    return quantity * price;
  }
}
