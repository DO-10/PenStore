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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("user")
@RequestMapping(PathConstants.MYPAGE)
public class MyPageController {
    @Autowired
    private  UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String myPage(@RequestParam String id, Model model) {
        List<Order> orderList =  orderService.getOrdersByUserId(id);

        model.addAttribute("orders", orderList);

        return Pages.MYPAGE;
    }
    @GetMapping("/{userId}")
    public String myPageByPath(@PathVariable String userId, Model model) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        model.addAttribute("orders", orders);
        return Pages.MYPAGE;
    }
    @PostMapping("/changeInfo")
    @ResponseBody
    public String myPageOperation(@ModelAttribute("userRequest") UserRequest userRequest, Model model) {
        String image_url = fileService.saveFile(userRequest.getAvatar(),"avatar");
        userRequest.setAvatarPath(image_url);
        userService.updateUser(userRequest);
        model.addAttribute("user", userService.getById(userRequest.getId()));
//        // 更新用户信息后，重新获取订单列表
//        List<Order> orderList = orderService.getOrdersByUserId(userRequest.getId());
//        model.addAttribute("orders", orderList);
        return "用户信息更新成功";
    }
    @PostMapping("/update")
    @ResponseBody
    public String updateAddress(
            @RequestParam String userId,
            @RequestParam String newAddress) {

        // 直接调用服务层（假设服务层不做任何校验）
        userService.addAddress(userId, newAddress);
        return "地址更新成功"; // 直接返回字符串
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteAddress(
            @RequestParam String userId,
            @RequestParam String address) {

        Map<String, Object> response = new HashMap<>();

            userService.deleteAddress(userId, address);
            response.put("status", "success");
            response.put("message", "地址删除成功");
            return ResponseEntity.ok(response);

    }

}
