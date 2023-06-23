package webs.hillel;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        int cartId = resultSet.getInt("cart_id");
        Cart cart = new Cart(cartId);

        return cart;
    }
}
