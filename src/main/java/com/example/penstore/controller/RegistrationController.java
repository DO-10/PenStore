package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.dto.UserRequest;
import com.example.penstore.service.impl.EmailService;
import com.example.penstore.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("verificationCode")//session
@RequestMapping(PathConstants.LOGIN)
public class RegistrationController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(){
        return Pages.LOGIN;
    }

    @PostMapping
    public String register(@ModelAttribute("userRequest") UserRequest userRequest, SessionStatus sessionStatus, Model model){
        String verificationCode = (String) model.getAttribute("verificationCode");
        System.out.println(verificationCode);
        if(!(emailService.authenticate(userRequest.getVerificationCode(), verificationCode))){
            model.addAttribute("verificationerror", "验证码错误，请重新输入！");
            return Pages.LOGIN; // 直接返回登录页面
        }

        sessionStatus.setComplete();//删除session中的验证码
        //将user存入数据库中
        userService.insertUser(userRequest);
        //注册完登录
        return Pages.LOGIN;

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
