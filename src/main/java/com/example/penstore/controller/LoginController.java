package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.domain.User;
import com.example.penstore.dto.UserRequest;
import com.example.penstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("user")
@RequestMapping(PathConstants.HOME)
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String signin(@ModelAttribute("userRequest") UserRequest userRequest, Model model) {
        User user = userService.signin(userRequest);
        if (user == null) {
            model.addAttribute("accounterror", "用户名或密码错误");
            return Pages.LOGIN;
        }
        model.addAttribute("user", user);
//        model.addAttribute("id",user.getId());//将id存入session
        return Pages.HOME;
    }




}


