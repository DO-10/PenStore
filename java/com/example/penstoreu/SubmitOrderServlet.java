//package com.example.penstoreu;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import models.OrderService;
//
//
//import java.io.IOException;
//
//@WebServlet("/submitOrderServlet")
//public class SubmitOrderServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 获取用户ID和订单地址
//        String userId = request.getParameter("userId");
//        String address = request.getParameter("address");
//        // 获取勾选的商品ID
//        String[] selectedProductIds = request.getParameterValues("selectedProducts");
//
//        // 处理订单逻辑，例如将订单信息存入数据库
//        OrderService orderService = new OrderService();
//        String orderId = orderService.createOrder(userId,address);
//        for (String productId : selectedProductIds) {
//            orderService.addOrderItem(orderId, productId);
//        }
//
//        // 显示成功提示
//        response.getWriter().write("success ID: " + userId);
//    }
//}
//
package com.example.penstoreu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.OrderService;
import java.io.IOException;

@WebServlet("/submitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户ID和订单地址
        String userId = request.getParameter("userId");
        String addressOption = request.getParameter("addressOption");
        String existingAddressId = request.getParameter("existingAddress");
        String newAddress = request.getParameter("newAddress");

        String address;
        if ("existing".equals(addressOption)) {
            address = existingAddressId;
        } else {
            address = newAddress;
        }

        // 获取勾选的商品ID
        String[] selectedProductIds = request.getParameterValues("selectedProducts");

        // 获取备注和电话信息
        String notes = request.getParameter("notes");
        String phone = request.getParameter("phone");

        // 处理订单逻辑，例如将订单信息存入数据库
        OrderService orderService = new OrderService();
        String orderId = orderService.createOrder(userId, address);
        for (String productId : selectedProductIds) {
            orderService.addOrderItem(orderId, productId);
        }

        // 更新订单的备注和电话信息
        orderService.updateOrderDetails(orderId, notes, phone);

        // 返回JSON响应
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");


        String htmlResponse = "<!DOCTYPE html>" +
                "<html lang='zh-CN'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<title>下单成功</title>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; text-align: center; }" +
                ".container { background: white; padding: 40px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); display: inline-block; }" +
                "h1 { color: #d4af7a; }" +
                "p { font-size: 18px; }" +
                ".order-id { font-weight: bold; color: #333; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>恭喜您，下单成功！</h1>" +
                "<p>您的订单 ID 是：<span class='order-id'>" + orderId + "</span></p>" +
                "<p>感谢您的购买！</p>" +
                "<p><a href='home.jsp'>返回首页</a></p>" +
                "</div>" +
                "</body>" +
                "</html>";

        response.getWriter().write(htmlResponse);
    }
}