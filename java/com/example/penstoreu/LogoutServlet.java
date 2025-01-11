package com.example.penstoreu;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 清除session中的用户名
        HttpSession session = request.getSession();
        session.removeAttribute("username");

        // 重定向到登录页面
        response.sendRedirect("home.jsp");
    }
}