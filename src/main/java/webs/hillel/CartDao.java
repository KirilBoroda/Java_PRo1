package webs.hillel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class CartDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Cart> cartRowMapper;

    @Autowired
    public CartDao(JdbcTemplate jdbcTemplate, RowMapper<Cart> cartRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.cartRowMapper = cartRowMapper;
    }

    public void addCart(Cart cart) {
        String sql = "INSERT INTO Carts (cart_id) VALUES (?)";

        jdbcTemplate.update(sql, cart.getCartId());
    }

    public void addToCart(int cartId, int productId) {
        String sql = "INSERT INTO CartProducts (cart_id, product_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, cartId, productId);
    }


    public void removeFromCart(int cartId, int productId) {
        String sql = "DELETE FROM CartProducts WHERE cart_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, cartId, productId);
    }

    public boolean isProductInCart(int cartId, int productId) {
        String sql = "SELECT COUNT(*) FROM CartProducts WHERE cart_id = ? AND product_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, cartId, productId);
        return count > 0;
    }

    public void deleteCartById(int cartId) {
        String sql = "DELETE FROM Carts WHERE cart_id = ?";
        jdbcTemplate.update(sql, cartId);
    }

    public Cart getCartById(int cartId) {
        String sql = "SELECT * FROM Carts WHERE cart_id = ?";
        Cart cart = jdbcTemplate.queryForObject(sql, new Object[]{cartId}, cartRowMapper);

        String productSql = "SELECT product_id FROM CartProducts WHERE cart_id = ?";
        List<Integer> productIds = jdbcTemplate.queryForList(productSql, new Object[]{cartId}, Integer.class);

        cart.setProductIds(productIds);

        return cart;
    }
}
