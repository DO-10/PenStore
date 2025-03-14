package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkerController {
    //以此来实现客服后台界面的跳转
    @GetMapping(PathConstants.Worker)
    public String customerServicePage() {
        return Pages.WORKER;
    }
}

