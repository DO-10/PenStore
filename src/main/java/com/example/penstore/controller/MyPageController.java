package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.dto.UserRequest;
import com.example.penstore.service.FileService;
import com.example.penstore.service.OrderService;
import com.example.penstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("orders", orderService.getOrdersByUserId(id));
        return Pages.MYPAGE;
    }
    @PostMapping("/changeInfo")
    public String myPageOperation(@ModelAttribute("userRequest") UserRequest userRequest, Model model) {
        String image_url = fileService.saveFile(userRequest.getAvatar(),"avatar");
        userRequest.setAvatarPath(image_url);
        userService.updateUser(userRequest);
        model.addAttribute("user", userService.getById(userRequest.getId()));
        return Pages.MYPAGE;
    }
}
