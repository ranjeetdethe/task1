import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;

    public Product(int productId, String productName, String productDescription, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class ShoppingCart {
    private ArrayList<Product> cartItems = new ArrayList<>();

    public void addToCart(Product product) {
        cartItems.add(product);
    }

    public void removeFromCart(Product product) {
        cartItems.remove(product);
    }

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getProductPrice();
        }
        return total;
    }
}

class Order {
    private int orderId;
    private User user;
    private ArrayList<Product> orderedProducts;

    public Order(int orderId, User user, ArrayList<Product> orderedProducts) {
        this.orderId = orderId;
        this.user = user;
        this.orderedProducts = orderedProducts;
    }

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Product> getOrderedProducts() {
        return orderedProducts;
    }
}

class OnlineShoppingCart {
    private static Connection connection;

    public static void main(String[] args) {
        connectToDatabase();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nOnline Shopping Cart");
            System.out.println("1. Product Catalog");
            System.out.println("2. User Authentication");
            System.out.println("3. Shopping Cart Management");
            System.out.println("4. Checkout Process");
            System.out.println("5. Order History");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayProductCatalog();
                    break;

                case 2:
                    authenticateUser();
                    break;

                case 3:
                    manageShoppingCart();
                    break;

                case 4:
                    checkoutProcess();
                    break;

                case 5:
                    viewOrderHistory();
                    break;

                case 0:
                    System.out.println("Exiting the Online Shopping Cart. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);

        closeDatabaseConnection();
        scanner.close();
    }

    private static void connectToDatabase() {
        try {
            // Assuming MySQL is used. You need to replace the URL, username, and password with your database credentials.
            String url = "jdbc:mysql://localhost:3306/shopping_cart";
            String username = "your_username";
            String password = "your_password";

            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayProductCatalog() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            System.out.println("Product Catalog:");
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String productDescription = resultSet.getString("product_description");
                double productPrice = resultSet.getDouble("product_price");

                Product product = new Product(productId, productName, productDescription, productPrice);
                System.out.println(product.getProductId() + ". " + product.getProductName() +
                        " - " + product.getProductDescription() + " - $" + product.getProductPrice());
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void authenticateUser() {
        // User authentication implementation goes here
        System.out.println("User Authentication functionality is not implemented in this example.");
    }

    private static void manageShoppingCart() {
        // Shopping cart management implementation goes here
        System.out.println("Shopping Cart Management functionality is not implemented in this example.");
    }

    private static void checkoutProcess() {
        // Checkout process implementation goes here
        System.out.println("Checkout Process functionality is not implemented in this example.");
    }

    private static void viewOrderHistory() {
        // Order history implementation goes here
        System.out.println("Order History functionality is not implemented in this example.");
    }
}

