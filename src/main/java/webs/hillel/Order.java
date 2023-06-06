package webs.hillel;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Objects;

public class Order {
    private int id;
    private String date;
    private double cost;
    private Product[] products;

    public Order(int id, String date, double cost, Product[] products) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.products = products;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.cost, cost) == 0 && Objects.equals(date, order.date) && Arrays.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, date, cost);
        result = 31 * result + Arrays.hashCode(products);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", cost=" + cost +
                ", products=" + Arrays.toString(products) +
                '}';
    }
}