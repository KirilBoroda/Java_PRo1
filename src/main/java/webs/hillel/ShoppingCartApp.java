package webs.hillel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    private void printMenu() {
        String menu = """
            1. Add product to cart
            2. Remove product from cart
            3. View cart
            4. Exit
            Enter your choice:""";
        LOGGER.info(menu);
    }

    @Override
    public void run(String... args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while (true) {
            printMenu();

            try {
                input = reader.readLine();
            } catch (IOException e) {
                LOGGER.error("An error occurred while reading input.", e);
                return;
            }

            switch (input) {
                case "1":
                    addProductToCart(reader);
                    break;
                case "2":
                    removeProductFromCart(reader);
                    break;
                case "3":
                    viewCart();
                    break;
                case "4":
                    LOGGER.info("Exiting the program.");
                    return;
                default:
                    LOGGER.info("Invalid choice. Please try again.");
            }
        }
    }

    private void addProductToCart(BufferedReader reader) {
        LOGGER.info("Enter product ID: ");
        int productId;
        try {
            productId = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            LOGGER.error("Invalid product ID entered.", e);
            return;
        }

        Product product = productRepository.getProductById(productId);
        if (product == null) {
            LOGGER.info("Product not found.");
        } else {
            shoppingCart.addProduct(product);
            LOGGER.info("Product added to cart.");
        }
    }

    private void removeProductFromCart(BufferedReader reader) {
        LOGGER.info("Enter product ID: ");
        int productId;
        try {
            productId = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            LOGGER.error("Invalid product ID entered.", e);
            return;
        }

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
