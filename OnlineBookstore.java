import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Author: " + author + " | Price: $" + price;
    }
}

class CartItem {
    private Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return book.getPrice() * quantity;
    }
}

class Customer {
    private String username;
    private String password;
    private List<CartItem> cart = new ArrayList<>();

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void addToCart(Book book, int quantity) {
        cart.add(new CartItem(book, quantity));
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void placeOrder() {
        System.out.println("Order Placed!");
        cart.clear();
    }
}

public class OnlineBookstore {
    private List<Book> books = new ArrayList<>();
    private List<Customer> customers = new ArrayList();
    private Customer currentCustomer = null;

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("Book " + (i + 1) + ": " + books.get(i));
        }
    }

    public static void main(String[] args) {
        OnlineBookstore bookstore = new OnlineBookstore();
        Scanner scanner = new Scanner(System.in);

        // Create some sample books
        bookstore.addBook(new Book("Book 1", "Author 1", 20.0));
        bookstore.addBook(new Book("Book 2", "Author 2", 15.0));
        bookstore.addBook(new Book("Book 3", "Author 3", 25.0));
        bookstore.addBook(new Book("Book 4", "Author 4", 50.0));
        bookstore.addBook(new Book("Book 5", "Author 5", 750.0));
        bookstore.addBook(new Book("Book 6", "Author 6", 200.0));

        while (true) {
            if (bookstore.currentCustomer == null) {
                System.out.println("\nMenu:");
                System.out.println("1. Register as a customer");
                System.out.println("2. Log in as a customer");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        Customer newCustomer = new Customer(username, password);
                        bookstore.registerCustomer(newCustomer);
                        System.out.println("Customer registered successfully!");
                        break;
                    case 2:
                        System.out.print("Enter username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String loginPassword = scanner.nextLine();
                        Customer foundCustomer = null;
                        for (Customer customer : bookstore.customers) {
                            if (customer.getUsername().equals(loginUsername)
                                    && customer.getPassword().equals(loginPassword)) {
                                foundCustomer = customer;
                                break;
                            }
                        }
                        if (foundCustomer != null) {
                            bookstore.currentCustomer = foundCustomer;
                            System.out.println("Logged in as " + foundCustomer.getUsername());
                        } else {
                            System.out.println("Login failed. Invalid credentials.");
                        }
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("\nWelcome Online Book Store................., "
                        + bookstore.currentCustomer.getUsername() + "!");
                System.out.println("Menu:");
                System.out.println("1. Display available books");
                System.out.println("2. Add a book to your cart");
                System.out.println("3. View your cart");
                System.out.println("4. Place an order");
                System.out.println("5. Log out");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        bookstore.displayBooks();
                        break;
                    case 2:
                        bookstore.displayBooks();
                        System.out.print("Enter the number of the book you want to add to your cart: ");
                        int bookChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (bookChoice > 0 && bookChoice <= bookstore.books.size()) {
                            Book selectedBook = bookstore.books.get(bookChoice - 1);
                            System.out.print("Enter the quantity: ");
                            int quantity = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            bookstore.currentCustomer.addToCart(selectedBook, quantity);
                            System.out.println("Book added to your cart!");
                        } else {
                            System.out.println("Invalid book selection.");
                        }
                        break;
                    case 3:
                        List<CartItem> cart = bookstore.currentCustomer.getCart();
                        if (cart.isEmpty()) {
                            System.out.println("Your cart is empty.");
                        } else {
                            System.out.println("Your Cart:");
                            double total = 0;
                            for (int i = 0; i < cart.size(); i++) {
                                CartItem item = cart.get(i);
                                System.out.println("Item " + (i + 1) + ": " + item.getBook() + " | Quantity: "
                                        + item.getQuantity() + " | Total Price: $" + item.getTotalPrice());
                                total += item.getTotalPrice();
                            }
                            System.out.println("Total Price: $" + total);
                        }
                        break;
                    case 4:
                        List<CartItem> customerCart = bookstore.currentCustomer.getCart();
                        if (customerCart.isEmpty()) {
                            System.out.println("Your cart is empty. Cannot place an order.");
                        } else {
                            bookstore.currentCustomer.placeOrder();
                            System.out.println("Order placed successfully!");
                        }
                        break;
                    case 5:
                        System.out.println("Logged out.");
                        bookstore.currentCustomer = null;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
