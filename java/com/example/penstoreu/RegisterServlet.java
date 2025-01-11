package com.example.penstoreu;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.RegisterHandler;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String inputCode = request.getParameter("verificationCode");
        String username = request.getParameter("username");
        String sessionCode = (String) request.getSession().getAttribute("verificationCode");

        if (sessionCode == null || !sessionCode.equals(inputCode)) {
            request.setAttribute("errorMessage", "验证码错误");
            request.setAttribute("showVerification", true);
            request.setAttribute("email", email);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // 验证码正确，继续完成注册
        RegisterHandler registerHandler = new RegisterHandler();
        if(registerHandler.registerUser(email, password, username)) {
            response.sendRedirect("log&sign.jsp");
        }
    }
}


