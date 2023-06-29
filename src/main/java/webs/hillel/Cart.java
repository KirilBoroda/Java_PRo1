package webs.hillel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }


    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        } else {
            throw new IllegalArgumentException("Invalid product. Cannot add null to the cart.");
        }
    }

    public boolean removeProduct(int id) {
        Optional<Product> productOptional = products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();

        if (productOptional.isPresent()) {
            products.remove(productOptional.get());
            return true;
        }

        return false;
    }


    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }
}
