package com.example.penstore.controller;

import com.example.penstore.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.penstore.constants.PathConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
@SessionAttributes("verificationCode")
@RequestMapping(PathConstants.VERIFY)
public class EmailController {
    @Autowired
    private EmailService emailService;  // 发送验证码的服务
    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> verify(@RequestParam String email, Model model) {
        String verificationCode = generateVerificationCode();
        model.addAttribute("verificationCode", verificationCode);
        emailService.sendVerificationEmail(email, verificationCode);
        return ResponseEntity.ok().build();
    }
    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000); // 生成6位随机验证码
    }
}
