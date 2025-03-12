package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PathConstants.SELLER)
public class SellerController {
    @Autowired
    private UserService userService;
    @GetMapping("/#{id}")//请求卖家页面
    public String seller(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return Pages.DASHBOARD;
    }
    @GetMapping(PathConstants.GOODSMAGAGEMENT+"/#{id}")//请求商品管理页面
    public String goodsManage(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return Pages.GOODSMANAGEMENT;
    }
}
