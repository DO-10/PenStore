package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.domain.Goods;
import com.example.penstore.domain.Order;
import com.example.penstore.domain.User;
import com.example.penstore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

import com.example.penstore.service.GoodsService;
import com.example.penstore.service.CartService;

import java.math.BigDecimal;
import java.util.List;

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
                           @RequestParam("quantity")int[] quantity,
                           Model model) {
        // 获取选中的商品信息
        List<Goods> orderItems = goodsService.getProductsWithCartQuantities(selectedProductIds);

        for (int i = 0; i < orderItems.size(); i++) {
            orderItems.get(i).setQuantity(String.valueOf(quantity[i]));
        }



        // 将订单信息和总价存入Model
        model.addAttribute("userId", userId);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("quantity", quantity);

        // 返回订单页面
        return Pages.ORDER;
    }
    @GetMapping("/getaddress")
    public ResponseEntity<?> getAddresses(@RequestParam("userId") String userId) {
        try {
            List<String> addresses = orderService.findAddressesByUserId(userId);

            // 使用Map或自定义对象构建结构化响应，Spring会自动转换为JSON
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("addresses", addresses); // 直接使用List，无需手动转换

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 错误响应也保持结构化
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }
    @PostMapping("/gocheckout")
    public String checkoutOrder(
            @RequestParam("addressType") String addressType,
            @RequestParam(value = "existingAddress", required = false) String existingAddress,
            @RequestParam(value = "newAddress", required = false) String newAddress,
            @RequestParam("selectedProducts") List<String> productIds,
            @RequestParam("notes") String notes,
            @RequestParam("phone") String phone,
            HttpSession session,
            Model model) {

        // 获取用户ID
        User user = (User) session.getAttribute("user");
        String userId = user.getId();

        // 处理地址
        String finalAddress = "existing".equals(addressType) ? existingAddress : newAddress;

        // 创建订单
        String orderId = orderService.createOrder(userId, finalAddress, notes, phone, productIds);

        // 返回结果
        model.addAttribute("orderId", orderId);
        return "order/success";
    }

}
