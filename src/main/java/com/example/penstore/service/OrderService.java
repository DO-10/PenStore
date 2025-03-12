package com.example.penstore.service;

import com.example.penstore.aspect.GenerateID;
import com.example.penstore.domain.Order;
import com.example.penstore.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.penstore.dto.OrderRequest;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;



    // 创建订单并返回订单ID（UUID）
    public String createOrder(String userId, String address) {
        String orderId = GenerateID.getCurrentOrderId();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserid(userId);
        orderRequest.setAddress(address);
        orderRequest.setOrderId(orderId);

        orderMapper.createOrder(orderRequest); // 调用 Mapper 方法
        return orderId; // 返回生成的订单ID
    }

    // 添加订单项
    public void addOrderItem(String orderId, String productId) {
        String quantity = orderMapper.getQuantityByProductId(productId); // 查询购物车中的数量
        BigDecimal price = orderMapper.getPriceByProductId(productId); // 查询产品价格

        if (quantity != null && price != null) {
            orderMapper.addOrderItem(orderId, productId, quantity, price); // 插入订单项
        }
    }

    // 获取用户订单
    public List<Order> getOrdersByUserId(String userId) {
        return orderMapper.getOrdersByUserId(userId); // 获取用户订单
    }

}
