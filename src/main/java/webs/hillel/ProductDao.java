
package webs.hillel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO Products (id, name, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice());
        System.out.println("Product added to the database.");
    }

    public void deleteProductById(int productId) {
        String sql = "DELETE FROM Products WHERE id = ?";
        jdbcTemplate.update(sql, productId);
        System.out.println("Product deleted from the database.");
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM Products WHERE id = ?";
        Product product = jdbcTemplate.queryForObject(sql, new Object[]{productId}, new ProductRowMapper());
        return product;
    }

    public List<Product> getAvailableProducts() {
        String sql = "SELECT * FROM Products";
        List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper());
        return products;
    }
}
