package com.example.penstoreu;//这个servlet用于接收访问商品详情页的请求

import objects.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ProductService;

import java.io.IOException;

public class ProductDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();


        // 调用服务类获取商品信息
        ProductService productService = new ProductService();
        Product product = productService.getProductById(id);

        // 将商品信息添加到请求中,session中
        request.setAttribute("product", product);

        // 转发到 productDisplay.jsp
        request.getRequestDispatcher("productdisplay.jsp").forward(request, response);
    }
}
