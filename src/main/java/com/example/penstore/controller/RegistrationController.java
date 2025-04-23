package com.example.penstore.controller;


import com.example.penstore.dto.UserRequest;
import com.example.penstore.service.impl.EmailService;
import com.example.penstore.service.impl.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@RestController
@SessionAttributes("verificationCode")//session
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest, HttpSession session){
        String verificationCode = (String) session.getAttribute("verificationCode");
        System.out.println(verificationCode);

        if(!(emailService.authenticate(userRequest.getVerificationCode(), verificationCode))){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("验证码错误，请重新输入");
        }

        session.removeAttribute("verificationCode");//删除session中的验证码
        //将user存入数据库中
        userService.insertUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("注册成功");
    }
    @RequestMapping("/nameCheck")
    @ResponseBody
    public String isEmailExist(@RequestParam("q") String email){
        if(userService.checkUsernameExists(email)){
            return "Exist";
        }else{
            return "";
        }


    }
}
