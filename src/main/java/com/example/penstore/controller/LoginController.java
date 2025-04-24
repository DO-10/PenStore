package com.example.penstore.controller;

import com.example.penstore.common.CommonResponse;
import com.example.penstore.common.ResponseCode;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.entity.Order;
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

import java.util.List;


@RestController
@SessionAttributes("user")
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<?> signin(@ModelAttribute  UserRequest userRequest, HttpSession session) {
//        User user = userService.signin(userRequest);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
//        }
//        session.setAttribute("user", user);
//        return ResponseEntity.ok(user);//返回用户信息
//
//    }
    @PostMapping("/login")
    public CommonResponse<User> signin(@ModelAttribute  UserRequest userRequest, HttpSession session) {
        User user = userService.signin(userRequest);
        if (user == null) {
            return CommonResponse.createForError(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDescription());
        }
        session.setAttribute("user", user);
        return CommonResponse.createForSuccess(user);//返回用户信息

    }
    @PostMapping("/login/get")
    public CommonResponse<User> myget(@SessionAttribute(name = "user", required = false) User user) {

        if (user == null) {
            return CommonResponse.createForError(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDescription());
        }

        return CommonResponse.createForSuccess(user);//返回用户信息

    }
}


