package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.domain.Order;
import com.example.penstore.dto.GoodsRequest;
import com.example.penstore.dto.OrderRequest;
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
    public String ordermanagement(@PathVariable String id, Model model) {
        String shop_id = "shopA";
        List<Order> orderList = orderService.getOrders(shop_id);
        model.addAttribute("orders", orderList);
        return Pages.ORDERMANAGEMENT;
    }
    @PostMapping(PathConstants.ORDERMANAGEMENT+"/{id}")
    public String searchOrder(@ModelAttribute("order")OrderRequest orderRequest, Model model) {
        List<Order> orderList = orderService.getOrdersByQuery(orderRequest);
        model.addAttribute("orders", orderList);
        return Pages.ORDERMANAGEMENT;
    }
    @GetMapping(PathConstants.ORDER+"/{status}/{id}")
    public String unshipped(@PathVariable String status, Model model, @PathVariable String id) {
        String shop_id = "shopA";//等待固件的工作
        List<Order> orderList = orderService.getOrders(status,shop_id);
        model.addAttribute("orders", orderList);
        model.addAttribute("status", status);//将状态添加到model中，显示该状态的独特内容
        return Pages.ORDERMANAGEMENT;
    }
    @PostMapping(PathConstants.ORDER+"/{status}/{id}")
    public String searchOrder(@ModelAttribute("order")OrderRequest orderRequest, Model model, @PathVariable String status) {
        List<Order> orderList = orderService.getOrdersByQuery(orderRequest);
        orderRequest.setStatus(status);//添加状态
        model.addAttribute("orders", orderList);
        model.addAttribute("status", status);
        return Pages.ORDERMANAGEMENT;
    }
}
