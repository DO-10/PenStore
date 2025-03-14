package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerServiceController {
    //以此来实现用户客服网页的跳转
    @GetMapping(PathConstants.CustomerService)
    public String customerServicePage() {
        return Pages.CustomerService;
    }
}