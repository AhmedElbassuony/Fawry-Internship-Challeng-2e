# 📚 Book Store Inventory System

A simple Java application that models a **Book Store inventory**, allowing the management of books (both paper and electronic), sales operations, and shipping/delivery services.

---

## 🚀 Features

- ✅ Add **PaperBooks** and **EBooks** to the inventory.
- ✅ Automatically **increase quantity** for PaperBooks with the same ISBN.
- ✅ Prevent duplicate EBooks with the same ISBN.
- ✅ Sell books with quantity and address/email validation.
- ✅ Send books using a **ShippingService** (for PaperBooks) or **MailService** (for EBooks).
- ✅ Remove books that are **outdated** based on their publication year and valid years.
- ✅ Fully **object-oriented design** with interfaces and abstract classes.
- ✅ Simple dependency management (services created inside the models).
- ✅ Unit tests with **JUnit 5**.

---

## 🔧 Project Structure

```
src/
├── enums/
│   └── FileType.java
├── interfaces/
│   ├── Sellable.java
│   └── SendingService.java
├── models/
│   ├── Book.java
│   ├── PaperBook.java
│   ├── EBook.java
│   ├── Inventory.java
│   └── DemoBook.java
├── services/
│   ├── BookStore.java
│   ├── ShippingService.java
│   └── MailService.java
└── test/
    └── BookStoreTest.java  ← Example tests using JUnit 5
```

---

## 🔨 Technologies Used

- Java 17+
- Object-Oriented Programming (Inheritance, Abstraction, Interfaces, Encapsulation)
- Design Patterns:
  - Strategy-like abstraction with `SendingService`
  - Dependency Injection recommended but not yet applied
- JUnit 5 for testing

---

## ⚙️ How to Run

### ▶️ Compile & Run (Basic Setup)

```bash
javac -d out src/**/*.java
java -cp out models.Main  # If you create a Main class to demo the app
```

### ▶️ Run Tests (JUnit 5)

If you're using IntelliJ IDEA or Eclipse:

- Right-click on the `test/` folder or on a test class → Run.

If using Maven:

```bash
mvn test
```

---

## ✅ Example Usage

```java
Inventory inventory = new Inventory();
BookStore store = new BookStore(inventory);

Book paperBook = new PaperBook("Clean Code", 2011, 20, 150.0, 10);
Book ebook = new EBook("Effective Java", 2018, 15, 100.0, FileType.PDF);

store.add(paperBook);
store.add(ebook);

store.buy(paperBook.getISBN(), 3, "user@example.com", "123 Street");
```




