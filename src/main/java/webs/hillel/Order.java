package webs.hillel;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private String date;

    private List<Product> products;

    public Order() {
    }

    public Order(int id, String date, List<Product> products) {
        this.id = id;
        this.date = date;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public double getCosts() {
        double cost = 0.0;
        for (Product product : products) {
            cost += product.getCost();
        }
        return cost;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
