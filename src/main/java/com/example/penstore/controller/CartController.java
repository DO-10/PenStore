package com.example.penstore.controller;


import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.domain.Goods;

import com.example.penstore.domain.User;
import com.example.penstore.dto.CartRequest;
import com.example.penstore.dto.UserRequest;
import com.example.penstore.service.CartService;
import com.example.penstore.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.penstore.constants.Pages;
import java.util.Map;
import java.util.HashMap;

import java.math.BigDecimal;

import java.util.List;

@Controller
@RequestMapping(PathConstants.CART)
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRequest userRequest;
    @GetMapping
    public String showCart(@SessionAttribute("user") User user, Model model) {
        //类型转换
        BeanUtils.copyProperties(user, userRequest);

        List<Goods> cartItems =cartService.getGoodsListByUserId(userRequest);// 获取购物车商品

        //价格
        BigDecimal totalPrice =cartService.calculateTotalPriceByUserId(userRequest.getId());
//        String userId = getUserId(); // 获取用户ID
//        String username = getUsername(); // 获取用户名
        String userId = userRequest.getId();
//        System.out.println("用户 ID: " + userId); // 打印用户 ID
        //List<Goods> goodsList = cartMapper.getGoodsListByUserId(userId);
        //System.out.println("查询结果: " + goodsList); // 打印查询结果

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("userId", userId);
//        model.addAttribute("username", username);
        return Pages.CART; // 返回视图名称
    }



    @PostMapping("/addtocart")
    public String addToCart(@ModelAttribute("cartRequest")CartRequest cartRequest, RedirectAttributes redirectAttributes) {
        cartService.addToCart(cartRequest);
        // 添加 Flash Attribute，用于显示操作信息
        redirectAttributes.addFlashAttribute("message", "ok");
        // 添加 product_id 参数
        redirectAttributes.addAttribute("id",cartRequest.getProduct_id());
        return "redirect:" + PathConstants.GOODS + PathConstants.GOODSDISPLAY;
    }

    //修改购物车商品数量
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateCart(@RequestParam String userId,
                                   @RequestParam String goodsId, // 注意：这里改为 goodsId
                                   @RequestParam String operation) {
        Map<String, Object> response = new HashMap<>();


        // 调用 Service 层处理逻辑

          int newQuantity = cartService.updateCart(userId, goodsId, operation);


        BigDecimal totalPrice = cartService.calculateTotalPriceByUserId(userId);

        // 将数据放入 Map
        response.put("newQuantity", newQuantity);
        response.put("totalPrice", totalPrice.toString());
        response.put("allChosen", cartService.isAllChosen(userId));


        return response;
    }
}
