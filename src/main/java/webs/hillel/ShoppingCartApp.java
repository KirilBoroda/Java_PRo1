package webs.hillel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Import(AppConfig.class)
public class ShoppingCartApp implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Cart shoppingCart;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartApp.class);

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
            System.out.println("5. View all");
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
                    break;
                case "5":
                    viewAllProducts();
                    break;
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
            logger.info("Product not found.");
        } else {
            shoppingCart.addProduct(product);
            logger.info("Product added to cart.");
        }
    }

    private void removeProductFromCart(Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());

        boolean removed = shoppingCart.removeProduct(productId);
        if (removed) {
            logger.info("Product removed from cart.");
        } else {
            logger.info("Product not found in cart.");
        }
    }

    private void viewCart() {
        logger.info("Cart contents:");
        for (Product product : shoppingCart.getProducts()) {
            logger.info(product.getName() + " - $" + product.getPrice());
        }
    }

    private void viewAllProducts() {
        logger.info("Cart contents:");
        List<Product> cartProducts = shoppingCart.getProducts();
        if (cartProducts.isEmpty()) {
            logger.info("Cart is empty.");
        } else {
            for (Product product : cartProducts) {
                logger.info(product.getId() + " - " + product.getName() + " - $" + product.getPrice());
            }
        }
    }


}
