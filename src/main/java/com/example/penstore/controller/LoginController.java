package com.example.penstore.controller;

import com.example.penstore.constants.PathConstants;
import com.example.penstore.entity.User;
import com.example.penstore.dto.UserRequest;
import com.example.penstore.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@SessionAttributes("user")
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> signin(@RequestBody  UserRequest userRequest, HttpSession session) {
        User user = userService.signin(userRequest);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
        session.setAttribute("user", user);
        return ResponseEntity.ok(user);//返回用户信息
    }
}


