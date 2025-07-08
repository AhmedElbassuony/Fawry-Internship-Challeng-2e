package interfaces;

public interface Sellable {
  public double sell(int quantity, String email, String address);

  public double getPrice();

  public void setPrice(double price);
}
