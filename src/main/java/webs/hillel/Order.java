package webs.hillel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private String date;
    private double total;
    private List<Product> products;

    public Order(int id, String date, double total, List<Product> products) {
        this.id = id;
        this.date = date;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.total, total) == 0 && Objects.equals(date, order.date) && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, total, products);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", total=" + total +
                ", products=" + products +
                '}';
    }
}