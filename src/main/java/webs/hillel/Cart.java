package webs.hillel;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int cartId;
    private List<Integer> productIds;

    public Cart(int cartId) {
        this.cartId = cartId;
        this.productIds = new ArrayList<>();
    }

    public int getCartId() {
        return cartId;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", productIds=" + productIds +
                '}';
    }
}
