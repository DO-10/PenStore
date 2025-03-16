package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.domain.Goods;
import com.example.penstore.domain.Order;
import com.example.penstore.domain.TransactionSnapshot;
import com.example.penstore.domain.User;
import com.example.penstore.service.OrderService;
import com.example.penstore.service.TransactionSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.nio.file.AccessDeniedException;
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
    // 新增的 TransactionSnapshotService 依赖注入
    @Autowired
    private TransactionSnapshotService snapshotService;


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

    // 新增的交易快照处理方法
    @GetMapping("/snapshot/{orderId}")
    public String getUserSnapshot(
            @PathVariable String orderId,
            @ModelAttribute("user") User user,
            Model model
    ) throws AccessDeniedException {
        TransactionSnapshot snapshot = snapshotService.getSnapshotByOrderId(orderId);
        if (!snapshot.getUserId().equals(user.getId())) {
            throw new AccessDeniedException("无权访问此快照");
        }
        model.addAttribute("snapshot", snapshot);
        return "user_snapshot";
    }

    // 商家查看商品快照（需校验商家身份）
    @GetMapping("/seller/snapshot/{productId}")
    public String getShopSnapshot(
            @PathVariable String productId,
            @ModelAttribute("user") User seller,
            Model model
    ) {
        List<TransactionSnapshot> snapshots =
                snapshotService.getSnapshotsByShopAndProduct(seller.getId(), productId);
        model.addAttribute("snapshots", snapshots);
        return "shop_snapshot_list";
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



        // 生成订单并传递商品列表
        String orderId = orderService.createOrder(userId, "默认地址", orderItems);



        // 将订单信息和总价存入Model
        model.addAttribute("userId", userId);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("totalPrice", totalPrice);

        // 返回订单页面
        return Pages.ORDER;
    }

}
