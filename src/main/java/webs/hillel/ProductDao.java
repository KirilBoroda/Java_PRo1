
package webs.hillel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Product> productRowMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate, RowMapper<Product> productRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRowMapper = productRowMapper;
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO Products (id, name, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice());
        LOGGER.info("Product added to the database.");
    }

    public void deleteProductById(int productId) {
        String sql = "DELETE FROM Products WHERE id = ?";
        jdbcTemplate.update(sql, productId);
        LOGGER.info("Product deleted from the database.");
    }


    public Product getProductById(int productId) {
        String sql = "SELECT * FROM Products WHERE id = ?";
        Product product = jdbcTemplate.queryForObject(sql, new Object[]{productId}, productRowMapper);
        return product;
    }

    public List<Product> getAvailableProducts() {
        String sql = "SELECT * FROM Products";
        List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper());
        return products;
    }
}
