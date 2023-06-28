package webs.hillel;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        initializeProducts();
    }

    private void initializeProducts() {
        products.add(new Product(1, "Product 1", 10.0));
        products.add(new Product(2, "Product 2", 15.0));
        products.add(new Product(3, "Product 3", 20.0));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}