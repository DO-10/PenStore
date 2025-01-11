package com.example.penstoreu;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import models.OrderService;

public class AddressGetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        OrderService orderService = new OrderService();
        List<String> addresses = orderService.getUserAddresses(userId);

        // 创建一个包含状态和地址列表的响应对象
        Response responseObj = new Response("success", addresses);

        // 设置响应类型为JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 使用ObjectMapper将响应对象序列化为JSON格式并返回
        new ObjectMapper().writeValue(response.getOutputStream(), responseObj);
    }

    // 响应数据类
    public static class Response {
        private String status;
        private List<String> addresses;

        public Response(String status, List<String> addresses) {
            this.status = status;
            this.addresses = addresses;
        }

        // Getter和Setter（如果需要）
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<String> addresses) {
            this.addresses = addresses;
        }
    }
}