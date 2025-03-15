package com.example.penstore.service;

import com.example.penstore.aspect.GenerateID;
import com.example.penstore.domain.Order;
import com.example.penstore.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.penstore.dto.OrderRequest;


import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;



    // 创建订单并返回订单ID（UUID）
    public String createOrder(String userId, String address) {
        String orderId = GenerateID.getCurrentOrderId();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUser_id(userId);
        orderRequest.setShipping_address(address);
        orderRequest.setOrder_id(orderId);

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
    // 获取全部订单
    public List<Order> getOrders(String shop_id) {
        return orderMapper.getOrders(shop_id);
    }
    //根据状态获取订单
    public List<Order> getOrders(String status, String shop_id) {
        return orderMapper.getOrdersByStatus(status, shop_id);
    }
    // 搜索订单
    public List<Order> getOrdersByQuery(OrderRequest orderRequest) {
        return orderMapper.getOrdersByQuery(orderRequest);
    }
//    public List<Order> getOrdersByOrderId(String orderId) {}

}
