package com.example.penstore.service;

import com.example.penstore.aspect.GenerateID;
import com.example.penstore.dao.CartMapper;
import com.example.penstore.dao.GoodsMapper;
import com.example.penstore.domain.Goods;
import com.example.penstore.domain.Order;
import com.example.penstore.dao.OrderMapper;
import com.example.penstore.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.penstore.dto.OrderRequest;
import java.math.BigDecimal;
import com.example.penstore.domain.OrderItem;



import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;



//    // 创建订单并返回订单ID（UUID）
//    public String createOrder(String userId, String address) {
//        String orderId = GenerateID.getCurrentOrderId();
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setUser_id(userId);
//        orderRequest.setShipping_address(address);
//        orderRequest.setOrder_id(orderId);
//
//        orderMapper.createOrder(orderRequest); // 调用 Mapper 方法
//        return orderId; // 返回生成的订单ID
//    }

    // 添加订单项
    public void addOrderItem(String orderId, String productId) {
        String quantity = orderMapper.getQuantityByProductId(productId); // 查询购物车中的数量
        BigDecimal price = orderMapper.getPriceByProductId(productId); // 查询产品价格

        if (quantity != null && price != null) {
            orderMapper.addOrderItem(orderId, productId, quantity, price); // 插入订单项
        }
    }
    public String createOrder(String userId, String address, String notes, String phone, List<String> productIds) {
        // 生成UUID订单ID
        String orderId = UUID.randomUUID().toString();

        // 创建订单实体
        Order order = new Order();
        order.setOrder_id(orderId);
        order.setUser_id(userId);
        order.setShipping_address(address);
        order.setNote(notes);
        order.setPhone(phone);
        order.setCreated_at(LocalDateTime.now());

        // 插入订单
        orderMapper.insertOrder(order);

        // 处理每个商品
        for (String productId : productIds) {
            // 获取购物车信息
            UserRequest userRequest = new UserRequest();
            userRequest.setId(userId);
            List<Goods> cartItem = cartMapper.getCartItemsByUserId(userRequest);

            // 获取商品价格
            Goods product = goodsMapper.getById(productId);

            // 创建订单项
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder_id(orderId);
            orderItem.setProduct_id(productId);
            int quantity = cartMapper.getProductQuantity(userId, productId);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(product.getPrice());
            String order_item_id = UUID.randomUUID().toString();
            orderItem.setOrder_items_id(order_item_id);

            // 插入订单项
            orderMapper.insertOrderItem(orderItem);

            // 清空购物车
            cartMapper.deleteProduct(userId, productId);
        }

        return orderId;
    }

//    // 其他服务方法（根据需求添加）
//    public List<Order> getOrdersByUserId(String userId) {
//        return orderMapper.findByUserId(userId);
//    }
//
//    public List<OrderItem> getOrderItems(String orderId) {
//        return orderItemMapper.findByOrderId(orderId);
//    }

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

    //找地址
    public List<String> findAddressesByUserId(String userId){
        return orderMapper.findAddress(userId);
    }


}
