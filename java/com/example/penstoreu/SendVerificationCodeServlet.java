package com.example.penstoreu;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import models.EmailService;

import java.io.IOException;
import java.util.Random;

public class SendVerificationCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String verificationCode = generateVerificationCode();

        // 将验证码保存到会话中
        request.getSession().setAttribute("verificationCode", verificationCode);

        // 调用发送邮件的逻辑
        EmailService.sendVerificationCode(email, verificationCode);

        // 设置页面显示验证码输入框
        request.setAttribute("showVerification", true);
        request.setAttribute("email", email);
        request.getRequestDispatcher("log&sign.jsp").forward(request, response);
    }

    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000); // 生成6位随机验证码
    }
}
