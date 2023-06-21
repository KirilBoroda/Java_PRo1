package webs.hillel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ShoppingCartApp implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final Cart shoppingCart;

    @Autowired
    public ShoppingCartApp(ProductRepository productRepository, Cart shoppingCart) {
        this.productRepository = productRepository;
        this.shoppingCart = shoppingCart;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApp.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("1. Add product to cart");
            System.out.println("2. Remove product from cart");
            System.out.println("3. View cart");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    addProductToCart(scanner);
                    break;
                case "2":
                    removeProductFromCart(scanner);
                    break;
                case "3":
                    viewCart();
                    break;
                case "4":
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addProductToCart(Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());

        Product product = productRepository.getProductById(productId);
        if (product == null) {
            LOGGER.info("Product not found.");
        } else {
            shoppingCart.addProduct(product);
            LOGGER.info("Product added to cart.");
        }
    }

    private void removeProductFromCart(Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());

        boolean removed = shoppingCart.removeProduct(productId);
        if (removed) {
            LOGGER.info("Product removed from cart.");
        } else {
            LOGGER.info("Product not found in cart.");
        }
    }

    private void viewCart() {
        LOGGER.info("Cart contents:");
        for (Product product : shoppingCart.getProducts()) {
            LOGGER.info(product.getName() + " - $" + product.getPrice());
        }
    }
    
}
