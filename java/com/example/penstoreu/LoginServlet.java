package com.example.penstoreu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.LoginHandler;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //从请求对象中得到用户名和密码
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 调用处理登录的类
        LoginHandler loginHandler = new LoginHandler();
        String userId = loginHandler.login(email, password);
        String username = loginHandler.getUsername(userId);

        if (userId != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);  //保存userId
            session.setAttribute("email",email);//保存email
            session.setAttribute("username", username);
            response.sendRedirect("home.jsp");// 登录成功后重定向home.jsp
        } else {
            request.setAttribute("errorMessage", "登录失败，请检查用户名和密码。");
            request.getRequestDispatcher("log&sign.jsp").forward(request, response); // 转发到登录页面
        }
    }
}
