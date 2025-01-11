package com.example.penstoreu;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ProductService;
import objects.Product;


import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query"); // 获取用户输入的关键字


        // 调用相关类来搜索商品
        ProductService productService = new ProductService();
        List<Product> products = productService.searchProducts(query); // 搜索商品

        // 将搜索结果添加到请求中
        request.setAttribute("products", products);

        // 转发请求到展示商品的 JSP
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
    }
}
