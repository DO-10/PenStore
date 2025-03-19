package com.example.penstore.service;

import com.example.penstore.dao.SalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service

public class SalesService {
    @Autowired
    private SalesMapper salesMapper;
    // 获取销售概览数据
    public Map<String, Object> getSalesOverview(LocalDate startDate, LocalDate endDate) {
        Map<String, Object> result = new HashMap<>();

        // 获取基础统计数据
        Map<String, Object> stats = salesMapper.getSalesStats(
                startDate.atStartOfDay(),
                endDate.atTime(23, 59, 59)
        );

        // 获取趋势数据
        List<Map<String, Object>> dailyTrend = salesMapper.getDailySalesTrend(
                startDate.atStartOfDay(),
                endDate.atTime(23, 59, 59)
        );

        // 获取商品销售排行
        List<Map<String, Object>> topProducts = salesMapper.getTopProducts(10);

        result.put("stats", stats);
        result.put("trend", dailyTrend);
        result.put("topProducts", topProducts);
        System.out.println("Trend数据: " + dailyTrend);
        return result;
    }

    // 获取实时销售额
    public BigDecimal getRealtimeSales() {
        return salesMapper.getTodaySales(LocalDate.now());
    }
}
