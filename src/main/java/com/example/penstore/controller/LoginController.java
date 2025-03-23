package com.example.penstore.controller;

import com.example.penstore.constants.PathConstants;
import com.example.penstore.entity.User;
import com.example.penstore.dto.UserRequest;
import com.example.penstore.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@SessionAttributes("user")
@RequestMapping(PathConstants.HOME)
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String signin(@ModelAttribute("userRequest") UserRequest userRequest,
                         RedirectAttributes redirectAttributes,
                         HttpSession session) {
        User user = userService.signin(userRequest);
        if (user == null) {
            // 将错误信息通过 RedirectAttributes 传递到下一个请求
            redirectAttributes.addFlashAttribute("accounterror", "用户名或密码错误");
            return "redirect:/login";  // 重定向到登录页面
        }
        session.setAttribute("user", user);
        redirectAttributes.addFlashAttribute("user", user);  // 将 user 信息传递到下一个请求
        return "redirect:/home";  // 重定向到主页
    }





}


