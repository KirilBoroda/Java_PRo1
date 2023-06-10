package webs.hillel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CoffeeOrderBoard {
    private static final Logger logger = LogManager.getLogger(CoffeeOrderBoard.class);

    private int nextOrderNumber = 1;
    private Map<Integer, Order> orders = new HashMap<>();

    public CoffeeOrderBoard() {
        logger.debug("CoffeeOrderBoard instance created.");
    }

    public void add(String customerName) {
        orders.put(nextOrderNumber, new Order(nextOrderNumber, customerName));
        logger.info("New order added: Order Number = {}, Customer Name = {}", nextOrderNumber, customerName);
        nextOrderNumber++;
    }

    public void deliver() {
        if (!orders.isEmpty()) {
            int orderNumber = orders.keySet().iterator().next();
            Order order = orders.get(orderNumber);
            orders.remove(orderNumber);
            logger.info("Order delivered: Order Number = {}, Customer Name = {}", order.getOrderNumber(), order.getCustomerName());
        } else {
            logger.warn("No orders to deliver.");
        }
    }

    public void deliver(int orderNumber) {
        Order order = orders.get(orderNumber);
        if (order != null) {
            orders.remove(orderNumber);
            logger.info("Order delivered: Order Number = {}, Customer Name = {}", order.getOrderNumber(), order.getCustomerName());
        } else {
            logger.warn("Order not found: Order Number = {}", orderNumber);
        }
    }

    public String draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("=============\n");
        sb.append("Num | Name\n");
        for (int orderNumber : orders.keySet()) {
            Order order = orders.get(orderNumber);
            sb.append(orderNumber).append("   | ").append(order.getCustomerName()).append("\n");
        }
        return sb.toString();
    }
}
