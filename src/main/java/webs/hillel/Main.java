package webs.hillel;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        ApplicationContext context = SpringApplication.run(Application.class, args);
        OrderController orderController = context.getBean(OrderController.class);

        Product product1 = new Product(1, "Product 1", 10.99);
        Product product2 = new Product(2, "Product 2", 15.99);
        Product product3 = new Product(3, "Product 3", 5.99);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Order order = new Order(1, "2023-06-01", 27.98, products);

        orderController.addOrder(order);

        Order retrievedOrder = orderController.getOrderById(1).getBody();

        System.out.println("Retrieved Order: " + retrievedOrder.getId());

        List<Order> allOrders = orderController.getAllOrders();
        System.out.println("All Orders:");
        for (Order ord : allOrders) {
            System.out.println(ord.getId());
        }
    }
}
