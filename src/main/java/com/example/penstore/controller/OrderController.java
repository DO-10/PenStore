package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.domain.Goods;
import com.example.penstore.domain.Order;
import com.example.penstore.domain.User;
import com.example.penstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;
import com.example.penstore.service.GoodsService;
import com.example.penstore.service.CartService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes("user")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;

    @PostMapping
    public String showOrders(@ModelAttribute("user") User user, Model model) {
        if (user != null) {
            String userId = user.getId(); // 获取用户 ID
            // 使用 userId 进行订单查询或其他操作
        } else {
            // 处理用户未登录的情况
            return "redirect:/login"; // 或者其他处理逻辑
        }

        // 这里可以添加逻辑来获取用户的订单
        List<Order> orderItems =orderService.getOrdersByUserId(user.getId()) ; // 假设你有这样的方法
        // 将订单添加到模型中以供视图使用
        model.addAttribute("orderItems", orderItems);

        return Pages.ORDER; // 返回订单视图
    }


    private List<Order> getOrdersForUser(User user) {
        // 这里实现您的订单查询逻辑
        return List.of(); // 返回空列表或实际的订单列表
    }

    //结算界面生成订单
    @PostMapping("/checkout")
    public String checkout(@RequestParam("totalPrice") BigDecimal totalPrice,
                           @RequestParam("selectedProducts") String[] selectedProductIds,
                           @RequestParam("userId") String userId,
                           Model model) {
        // 获取选中的商品信息
        List<Goods> orderItems = goodsService.getProductsWithCartQuantities(selectedProductIds);



        // 将订单信息和总价存入Model
        model.addAttribute("userId", userId);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("totalPrice", totalPrice);

        // 返回订单页面
        return Pages.ORDER;
    }

}
