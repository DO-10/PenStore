package com.example.penstore.controller;

import com.example.penstore.entity.Sms;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sms")
public class SmsController {
    @PostMapping("/send")
    public void sms(@RequestBody Sms sms) {
        int appid = 1400182000;
        String appKey = "b0f2c3d4e5f6g7h8i9j0k1l2m3n4o5p6";
    }
}
