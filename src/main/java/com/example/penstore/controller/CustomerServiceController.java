package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.entity.User;
import com.example.penstore.service.impl.CustomerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Map;

@Controller
public class CustomerServiceController {
    @Autowired
    private CustomerListService customerListService;
    //以此来实现用户客服网页的跳转
    @GetMapping(PathConstants.CustomerService)
    public String customerServicePage(Model model,@SessionAttribute("user") User currentUser) {
        List<User> users = customerListService.getUsers();
        String myUserId = currentUser.getId();
        List<Map<String, Object>> messages = customerListService.getLastMessage(users, myUserId);
        model.addAttribute("users", users);
        model.addAttribute("messages", messages);
        return Pages.CustomerService;
    }
}