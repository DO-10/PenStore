package com.example.penstoreu;

import objects.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.CartService;

import java.io.IOException;
import java.util.List;

public class CartServlet extends HttpServlet {
    private CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 尝试从 session 中获取 userId
        HttpSession session = request.getSession(false);
        String userId = (session != null) ? (String) session.getAttribute("userId") : null;

        // 如果 session 中没有 userId，则尝试从请求参数中获取
//        if (userId == null) {
//            userId = request.getParameter("userId");
//        }

        if (userId != null) {
            // 获取该用户的购物车商品
            request.setAttribute("userId", userId);
            List<Product> cartItems = cartService.getCartItemsByUserId(userId);
            request.setAttribute("cartItems", cartItems);
//            String[] selectedProductIds = (String[]) request.getAttribute("selectedProductIds");
//            request.setAttribute("selectedProductIds",selectedProductIds);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            // 如果没有找到 userId，重定向到登录页面
            response.sendRedirect("log&sign.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getAttribute("userId");
        if (userId != null) {
            // 获取该用户的购物车商品
            request.setAttribute("userId", userId);
            List<Product> cartItems = cartService.getCartItemsByUserId(userId);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}
