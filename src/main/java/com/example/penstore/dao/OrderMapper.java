package com.example.penstore.dao;
import java.util.List;
import com.example.penstore.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.example.penstore.dto.OrderRequest;

import java.math.BigDecimal;
import java.util.List;
@Mapper

public interface OrderMapper {
    String createOrder(OrderRequest orderRequest);
    String getQuantityByProductId(String productId);
    BigDecimal getPriceByProductId(String orderId);
    String addOrderItem(String orderId, String productId, String quantity,BigDecimal price);
    List<Order> getOrdersByUserId(String userId);


}
