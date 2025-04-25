package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.entity.Order;
import com.example.penstore.dto.UserRequest;
import com.example.penstore.service.impl.FileService;
import com.example.penstore.service.impl.OrderService;
import com.example.penstore.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/mypage")
public class MyPageController {
    @Autowired
    private  UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private OrderService orderService;
//获取用户列表订单（通过查询参数）
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersByQuery(@RequestParam String userId) {
        List<Order> orderList = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orderList);
    }

    // 获取用户订单列表（通过路径参数）
    @GetMapping("/orders/{userId}")
    public ResponseEntity<List<Order>> getOrdersByPath(@PathVariable String userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    // 修改用户信息（携带头像）
    @PostMapping("/changeInfo")
    public ResponseEntity<?> updateUserInfo(
            @RequestParam("id") String id,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "phone", required = false) String phone
    ) {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(id);
        userRequest.setUsername(username);
        userRequest.setPassword(password);
        userRequest.setPhone(phone);

        if (avatar != null && !avatar.isEmpty()) {
            String imageUrl = fileService.saveFile(avatar, "avatar");
            userRequest.setAvatarPath(imageUrl);
        }

        userService.updateUser(userRequest);
        return ResponseEntity.ok(Map.of("message", "用户信息更新成功"));
    }

    // 添加地址
    @PostMapping("/address")
    public ResponseEntity<?> updateAddress(
            @RequestParam String userId,
            @RequestParam String newAddress
    ) {
        userService.addAddress(userId, newAddress);
        return ResponseEntity.ok(Map.of("message", "地址更新成功"));
    }

    // 删除地址
    @DeleteMapping("/address")
    public ResponseEntity<?> deleteAddress(
            @RequestParam String userId,
            @RequestParam String address
    ) {
        userService.deleteAddress(userId, address);
        return ResponseEntity.ok(Map.of("message", "地址删除成功"));
    }

}
