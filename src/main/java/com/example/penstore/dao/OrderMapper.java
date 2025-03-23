package com.example.penstore.dao;
import java.util.List;

import com.example.penstore.entity.Goods;
import com.example.penstore.entity.Order;
import com.example.penstore.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import com.example.penstore.dto.OrderRequest;

import java.math.BigDecimal;

@Mapper

public interface OrderMapper {
    List<Order> getById(String id);
    void createOrder(OrderRequest orderRequest);
    String getQuantityByProductId(String productId);
    BigDecimal getPriceByProductId(String orderId);
    void addOrderItem(String orderId, String productId, String quantity,BigDecimal price);
    List<Order> getOrdersByUserId(String userId);
    List<Goods> getGoodsByOrderId(String orderId);
    List<Order> getOrders(String shop_id);
    List<Order> getOrdersByStatus(String status, String shop_id);
    List<Order> getOrdersByQuery(OrderRequest orderRequest);
    List<Order> getOrdersByOrderId(String orderId);
    List<String>findAddress(String userId);
    void insertOrder(OrderRequest orderRequest);
    void insertOrderItem(OrderItem orderItem);
    void payOrder(String orderId);
    void deliverOrder(String orderId);
    void closeOrder(String orderId);


}
