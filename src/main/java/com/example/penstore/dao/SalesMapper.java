package com.example.penstore.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface SalesMapper {
    Map<String, Object> getSalesStats(@Param("start") LocalDateTime start,
                                      @Param("end") LocalDateTime end);
    List<Map<String, Object>> getDailySalesTrend(@Param("start") LocalDateTime start,
                                                 @Param("end") LocalDateTime end);
    List<Map<String, Object>> getTopProducts(int limit);

    BigDecimal getTodaySales(LocalDate today);
}
