package com.example.penstoreu;

import connect.DatabaseConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/changeAddressServlet")
public class ChangeAddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应的字符编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        // 获取参数
        String userId = request.getParameter("userId");
        String newAddress = request.getParameter("newAddress");

        if (userId == null || newAddress == null || newAddress.trim().isEmpty()) {
            response.getWriter().write("参数无效！");
            return;
        }

        try (Connection conn = DatabaseConnectionManager.getConnection()) {
            // 更新地址
            String sql = "UPDATE users SET address = ? WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, newAddress);
                ps.setString(2, userId);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    response.getWriter().write("地址更新成功！");
                } else {
                    response.getWriter().write("地址更新失败，请检查用户 ID 是否正确。");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("服务器发生错误！");
        }
    }
}
