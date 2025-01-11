package com.example.penstoreu;

import objects.Order;
import objects.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.OrderService;
import models.ProductService;
import models.UserService;
import objects.User;

import java.io.IOException;
import java.util.List;

public class MyPageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        ProductService productService = new ProductService();

        User user = userService.getUserById(userId);
        request.setAttribute("user",user);

        String username = userService.getUsernameById(userId);
        request.setAttribute("username",username);

        List<Order> orders = orderService.getOrdersByUserId(userId);
        request.setAttribute("orders",orders);
        for (Order order : orders) {
            List<Product> products = productService.getProductsByOrderId(order.getId());
            request.setAttribute("products", products);
        }

        // 转发到 JSP 页面
        request.getRequestDispatcher("mypage.jsp").forward(request, response);


    }

}
