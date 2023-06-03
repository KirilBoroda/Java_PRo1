package webs.hillel;

import java.util.ArrayList;
import java.util.List;

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
}