package com.example.penstoreu;

import models.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前用户的 ID（假设已经存储在 session 中）
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");


        // 如果未登录，重定向到登录页面
        if (userId == null) {
            response.sendRedirect("log&sign.jsp");
            return;
        }

        // 获取产品 ID
        int productId = Integer.parseInt(request.getParameter("productId"));

        // 调用 CartService 将商品添加到购物车
        CartService cartService = new CartService();
        boolean added = cartService.addToCart(userId, productId);

        // 设置成功/失败消息并重定向到产品详细页面
        if (added) {
            response.sendRedirect("productDetailServlet?id=" + productId + "&message=added");
        } else {
            response.sendRedirect("productDetailServlet?id=" + productId + "&message=failed");
        }

    }
}
