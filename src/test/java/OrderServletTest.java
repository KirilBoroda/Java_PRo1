//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import webs.hillel.Order;
//import webs.hillel.OrderServlet;
//import webs.hillel.Product;
//
//import java.io.PrintWriter;
//
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.mockito.Mockito.*;
//
//class OrderServletTest {
//    @Mock
//    private HttpServletRequest request;
//    @Mock
//    private HttpServletResponse response;
//    @InjectMocks
//    private OrderServlet servlet;
//
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testDoGet_withExistingOrder() throws Exception {
//        // Setup
//        int orderId = 1;
//        when(request.getPathInfo()).thenReturn("/" + orderId);
//        when(response.getWriter()).thenReturn(mock(PrintWriter.class));
//
//        // Execute
//        servlet.doGet(request, response);
//
//
//        verify(response).setContentType("application/json");
//        verify(response.getWriter()).println(orders.get(orderId));
//    }
//
//    @Test
//    void testDoGet_withNonExistingOrder() throws Exception {
//        // Setup
//        int orderId = 2;
//        when(request.getPathInfo()).thenReturn("/" + orderId);
//        when(response.getWriter()).thenReturn(mock(PrintWriter.class));
//
//        // Execute
//        servlet.doGet(request, response);
//
//        // Verify
//        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
//    }
//
//    // Add more test methods for other CRUD operations
//
//    private Map<Integer, Order> orders = new HashMap<>();
//
//    private void initializeTestData() {
//        // Initialize test data
//        Order order1 = new Order(1, "2023-06-05", 100.0, new Product[]{new Product(1, "Product 1", 10.0)});
//        Order order2 = new Order(2, "2023-06-06", 200.0, new Product[]{new Product(2, "Product 2", 20.0)});
//        orders.put(order1.getId(), order1);
//        orders.put(order2.getId(), order2);
//    }
//}
//
