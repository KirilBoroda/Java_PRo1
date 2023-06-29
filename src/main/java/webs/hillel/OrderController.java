package webs.hillel;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Component
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String sayHello() {
        return "Hello, world!";
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable int id) {
        return orderRepository.getOrderById(id);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @PostMapping("/orders")
    public void addOrder(@RequestBody Order order) {
        orderRepository.addOrder(order);
    }
}
