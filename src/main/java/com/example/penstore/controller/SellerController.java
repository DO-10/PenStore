package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.domain.Order;
import com.example.penstore.dto.GoodsRequest;
import com.example.penstore.service.GoodsService;
import com.example.penstore.service.OrderService;
import com.example.penstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(PathConstants.SELLER)
public class SellerController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")//请求卖家页面
    public String seller(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return Pages.DASHBOARD;
    }
    @GetMapping(PathConstants.SHOPMANAGEMENT+"/{id}")//请求店铺管理页面
    public String shopManagement(@PathVariable String id, Model model) {
        return Pages.SHOPMANAGEMENT;
    }
    @GetMapping(PathConstants.GOODSMANAGEMENT+"/{id}")//请求商品管理页面
    public String goodsManage(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return Pages.GOODSMANAGEMENT;
    }
    @PostMapping(PathConstants.GOODSMANAGEMENT+"/{id}")//请求添加商品
    public String addGoods(@ModelAttribute("goods") GoodsRequest goodsRequest, @PathVariable String id) {
        goodsRequest.setSeller(id);
        goodsService.insertGoods(goodsRequest);
        return Pages.SHOPMANAGEMENT;
    }
    @GetMapping(PathConstants.ORDERMANAGEMENT+"/{id}")
    public String ordermanagement(Model model) {
        List<Order> orderList = orderService.getOrders();
        model.addAttribute("orders", orderList);
        return Pages.ORDERMANAGEMENT;
    }
}
