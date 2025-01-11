package com.example.penstoreu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import models.CartService;
import org.json.JSONObject;

public class ChangeCartServlet extends HttpServlet {
    private CartService cartService = new CartService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 userId 和选中的产品 ID
        String userId = request.getParameter("userId");
        String[] selectedProductIds = request.getParameterValues("selectedProducts");
        String operation = request.getParameter("operation");
        String productId = request.getParameter("productId");



        //更新商品数量
        if (operation != null) {
            if ("delete".equals(operation)) {
                // 删除商品
                cartService.deleteProduct(userId, productId);
            } else if ("choose".equals(operation)) {
                cartService.updateIsChosen(userId, productId, true);  // 设置 isChosen 为 true
            } else if ("unchoose".equals(operation)) {
                cartService.updateIsChosen(userId, productId, false); // 设置 isChosen 为 false
            } else {
                cartService.updateQuantity(userId, productId, operation);
            }
        }
        // 计算该用户下 ischosen = 1 的商品总价格
        BigDecimal totalPrice = cartService.calculateTotalPriceByUserId(userId);
        System.out.println(totalPrice);

        request.setAttribute("userId",userId);
        request.setAttribute("totalPrice", totalPrice);

        int newQuantity = cartService.getProductQuantity(userId, productId);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("newQuantity", newQuantity);
        jsonResponse.put("totalPrice", totalPrice.toString());

        // 设置响应类型为 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 将 JSON 响应写入输出流
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}
