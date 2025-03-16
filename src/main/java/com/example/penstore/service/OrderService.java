package com.example.penstore.service;

import com.example.penstore.aspect.GenerateID;
import com.example.penstore.entity.Order;
import com.example.penstore.entity.Goods;
import com.example.penstore.entity.TransactionSnapshot;
import com.example.penstore.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.penstore.dto.OrderRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TransactionSnapshotService snapshotService;

    // 创建订单并生成交易快照
    public String createOrder(String userId, String address, List<Goods> goodsList) {
        if (goodsList == null || goodsList.isEmpty()) {
            throw new IllegalArgumentException("商品列表不能为空");
        }

        String orderId = GenerateID.getCurrentOrderId();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUser_id(userId);
        orderRequest.setShipping_address(address);
        orderRequest.setOrder_id(orderId);
        orderMapper.createOrder(orderRequest);


        // 为每个商品生成独立快照
        for (Goods goods : goodsList) {
            TransactionSnapshot snapshot = new TransactionSnapshot();
            snapshot.setSnapshotId(UUID.randomUUID().toString());
            snapshot.setOrderId(orderId);
            snapshot.setUserId(userId);
            snapshot.setProductId(goods.getId());
            //snapshot.setShopId(goods.getShopId()); // 假设Goods类有shopId字段
            snapshot.setPrice(goods.getPrice());
            snapshot.setQuantity(Integer.parseInt(goods.getQuantity()));
            snapshot.setTotalPrice(goods.getPrice().multiply(new BigDecimal(goods.getQuantity())));
            snapshotService.createSnapshot(snapshot);
        }

        // 计算订单总金额
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Goods goods : goodsList) {
            BigDecimal quantity = new BigDecimal(goods.getQuantity());
            totalPrice = totalPrice.add(goods.getPrice().multiply(quantity));
        }

        // 生成交易快照
        TransactionSnapshot snapshot = new TransactionSnapshot();
        snapshot.setSnapshotId(UUID.randomUUID().toString());
        snapshot.setOrderId(orderId);
        snapshot.setUserId(userId);
        snapshot.setProductId(goodsList.get(0).getId()); // 示例取第一个商品ID
        snapshot.setTotalPrice(totalPrice);
        snapshotService.createSnapshot(snapshot);

        return orderId;
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
