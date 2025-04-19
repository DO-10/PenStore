package com.example.penstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    // 发送验证码到用户邮箱
    public void sendVerificationEmail(String toEmail, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2959209045@qq.com");  // 发件人邮箱
        message.setTo(toEmail);  // 收件人邮箱
        message.setSubject("验证码");  // 邮件主题
        message.setText("您的验证码是：" + verificationCode);  // 邮件内容

        // 发送邮件
        javaMailSender.send(message);
    }
    // 判断验证码正确
    public boolean authenticate(String input, String verificationCode) {
        return input.equals(verificationCode);
    }
}
