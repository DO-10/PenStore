package com.example.penstore.dao;
import java.util.List;
import com.example.penstore.domain.Order;
import com.example.penstore.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import com.example.penstore.dto.OrderRequest;

import java.math.BigDecimal;

@Mapper

public interface OrderMapper {
    String createOrder(OrderRequest orderRequest);
    String getQuantityByProductId(String productId);
    BigDecimal getPriceByProductId(String orderId);
    String addOrderItem(String orderId, String productId, String quantity,BigDecimal price);
    List<Order> getOrdersByUserId(String userId);
    List<Order> getOrders(String shop_id);
    List<Order> getOrdersByStatus(String status, String shop_id);
    List<Order> getOrdersByQuery(OrderRequest orderRequest);
    List<Order> getOrdersByOrderId(String orderId);
    List<String>findAddress(String userId);
    void insertOrder(Order order);
    void insertOrderItem(OrderItem orderItem);


}
