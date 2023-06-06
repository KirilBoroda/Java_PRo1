package webs.hillel;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/orders")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<Integer, Order> orders = new HashMap<>();
    private int nextOrderId = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            try {
                int orderId = Integer.parseInt(pathInfo.substring(1));
                Order order = orders.get(orderId);
                if (order != null) {
                    response.setContentType("application/json");
                    PrintWriter writer = response.getWriter();
                    writer.println(order.toJson());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Order order = parseOrder(request);
            order.setId(nextOrderId++);
            orders.put(order.getId(), order);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            try {
                int orderId = Integer.parseInt(pathInfo.substring(1));
                Order existingOrder = orders.get(orderId);
                if (existingOrder != null) {
                    Order updatedOrder = parseOrder(request);
                    updatedOrder.setId(existingOrder.getId());
                    orders.put(updatedOrder.getId(), updatedOrder);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            try {
                int orderId = Integer.parseInt(pathInfo.substring(1));
                Order existingOrder = orders.get(orderId);
                if (existingOrder != null) {
                    orders.remove(existingOrder.getId());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private Order parseOrder(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String date = request.getParameter("date");
        double cost = Double.parseDouble(request.getParameter("cost"));
        String[] productNames = request.getParameterValues("products");
        Product[] products = new Product[productNames.length];
        for (int i = 0; i < productNames.length; i++) {
            products[i] = new Product(i + 1, productNames[i], 0.0);
        }
        return new Order(id, date, cost, products);
    }
}
