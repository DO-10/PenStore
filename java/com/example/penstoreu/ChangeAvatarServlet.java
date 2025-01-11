package com.example.penstoreu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.UserService;

import java.io.IOException;

@WebServlet("/changeAvatar")
@MultipartConfig // 支持文件上传
public class ChangeAvatarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId"); // 假设用户 ID 从请求中获取
        Part filePart = request.getPart("avatar"); // 获取头像文件部分

        // 实例化 UserService 并调用 changePhoto 方法
        UserService userService = new UserService();
        String avatarUrl = userService.changePhoto(userId, filePart,request);

        if (avatarUrl != null) {
            request.setAttribute("avatarUrl", avatarUrl);
            response.getWriter().println("头像上传成功: " + avatarUrl);
            // 可以将用户重定向到个人信息页面或继续操作
        } else {
            response.getWriter().println("头像上传失败，请重试。");
        }
    }
}
