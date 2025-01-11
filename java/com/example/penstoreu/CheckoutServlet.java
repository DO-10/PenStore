package com.example.penstoreu;

import objects.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CartService;
import models.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取选中的商品ID
        String[] selectedProductIds = request.getParameterValues("selectedProducts");
        String userId = request.getParameter("userId");

        // 从数据库或其他服务获取对应的商品信息
        ProductService productService = new ProductService();
        List<Product> orderItems = productService.getProductsWithCartQuantities(selectedProductIds);

        // 计算总价
        CartService cartService = new CartService();
        BigDecimal totalPrice = cartService.calculateTotalPriceByUserId(userId);

        // 将订单信息和总价存入请求属性
        request.setAttribute("userId",userId);
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("totalPrice", totalPrice);

        // 转发到订单页面
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }
}

