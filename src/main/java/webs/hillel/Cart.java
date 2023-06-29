package webs.hillel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cartId == cart.cartId && Objects.equals(productIds, cart.productIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productIds);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", productIds=" + productIds +
                '}';
    }
}
