package com.example.penstoreu;//此servlet用于得到用户来自分类连接对productlist的访问
import objects.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("category_id");

        // 调用 ProductService 获取符合的商品列表
        ProductService productService = new ProductService();
        List<Product> products = productService.getProductsByCategoryId(Integer.parseInt(categoryId));

//        HttpSession session = request.getSession(false);
//        session.setAttribute("products",products);

        // 将商品列表设置为请求属性
        request.setAttribute("products", products);

        // 转发到 productList.jsp 页面进行展示
        request.getRequestDispatcher("productlist.jsp").forward(request, response);
    }
}
