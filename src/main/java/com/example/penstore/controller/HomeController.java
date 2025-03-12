package com.example.penstore.controller;

import com.example.penstore.constants.PathConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PathConstants.HOME)
public class HomeController {
    @GetMapping
    public String home() {
        return "home";
    }

}
