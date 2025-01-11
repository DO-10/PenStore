package com.example.penstoreu;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ProductService;
import objects.Product;


import java.io.IOException;
import java.util.List;

public class SearchInputServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        ProductService productService = new ProductService();
        List<Product> products = productService.searchProducts(query);
//        // 根据查询参数执行搜索逻辑
//        List<String> suggestions = getSuggestions(query);
        // 将搜索建议以 JSON 格式返回
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), products);
    }


}
