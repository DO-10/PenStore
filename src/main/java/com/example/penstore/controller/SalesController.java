package com.example.penstore.controller;

import com.example.penstore.constants.Pages;
import com.example.penstore.constants.PathConstants;
import com.example.penstore.service.impl.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
@Controller
@RequestMapping(PathConstants.SALES)
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping

    public String salesDashboard(@RequestParam(required = false) String period, Model model) {
        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();

        if ("month".equals(period)) {
            startDate = LocalDate.now().minusMonths(1);
        } else if ("year".equals(period)) {
            startDate = LocalDate.now().minusYears(1);
        }

        Map<String, Object> salesData = salesService.getSalesOverview(startDate, endDate);
        BigDecimal realtimeSales = salesService.getRealtimeSales();
        model.addAttribute("realtimeSales", realtimeSales);
        model.addAttribute("salesData", salesData);
        model.addAttribute("period", period != null ? period : "week");
        return Pages.SALE;

    }
}
