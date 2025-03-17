package com.example.penstore.service;

import com.example.penstore.aspect.GenerateID;
import com.example.penstore.dao.CartMapper;
import com.example.penstore.dao.GoodsMapper;
import com.example.penstore.domain.Goods;
import com.example.penstore.domain.Order;
import com.example.penstore.domain.Goods;
import com.example.penstore.domain.TransactionSnapshot;
import com.example.penstore.dao.OrderMapper;
import com.example.penstore.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.penstore.dto.OrderRequest;
import java.math.BigDecimal;
import com.example.penstore.domain.OrderItem;



import java.time.LocalDateTime;
import java.util.Date;
import java.math.BigDecimal;
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

    @Autowired
    private TransactionSnapshotService snapshotService;

//    // 创建订单并生成交易快照
//    public String createOrder(String userId, String address, List<Goods> goodsList) {
//        if (goodsList == null || goodsList.isEmpty()) {
//            throw new IllegalArgumentException("商品列表不能为空");
//        }
//
////    // 创建订单并返回订单ID（UUID）
////    public String createOrder(String userId, String address) {
////        String orderId = GenerateID.getCurrentOrderId();
////        OrderRequest orderRequest = new OrderRequest();
////        orderRequest.setUser_id(userId);
////        orderRequest.setShipping_address(address);
////        orderRequest.setOrder_id(orderId);
////
////        orderMapper.createOrder(orderRequest); // 调用 Mapper 方法
////        return orderId; // 返回生成的订单ID
////    }
//        String orderId = GenerateID.getCurrentOrderId();
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setUser_id(userId);
//        orderRequest.setShipping_address(address);
//        orderRequest.setOrder_id(orderId);
//        orderMapper.createOrder(orderRequest);
//
//
//        // 为每个商品生成独立快照
//        for (Goods goods : goodsList) {
//            TransactionSnapshot snapshot = new TransactionSnapshot();
//            snapshot.setSnapshotId(UUID.randomUUID().toString());
//            snapshot.setOrderId(orderId);
//            snapshot.setUserId(userId);
//            snapshot.setProductId(goods.getId());
//            //snapshot.setShopId(goods.getShopId()); // 假设Goods类有shopId字段
//            snapshot.setPrice(goods.getPrice());
//            snapshot.setQuantity(Integer.parseInt(goods.getQuantity()));
//            snapshot.setTotalPrice(goods.getPrice().multiply(new BigDecimal(goods.getQuantity())));
//            snapshotService.createSnapshot(snapshot);
//        }
//
//        // 计算订单总金额
//        BigDecimal totalPrice = BigDecimal.ZERO;
//        for (Goods goods : goodsList) {
//            BigDecimal quantity = new BigDecimal(goods.getQuantity());
//            totalPrice = totalPrice.add(goods.getPrice().multiply(quantity));
//        }
//        return orderId;
//    }

    public String createOrder(OrderRequest orderRequest) {
        // 生成UUID订单ID
        String orderId = UUID.randomUUID().toString();
        orderRequest.setOrder_id(orderId);
        orderRequest.setCreated_at(LocalDateTime.now());



//        // 创建订单实体
//        Order order = new Order();
//        order.setOrder_id(orderId);
//        order.setUser_id(userId);
//        order.setShipping_address(address);
//        order.setNote(notes);
//        order.setPhone(phone);
//        order.setCreated_at(LocalDateTime.now());

        // 插入订单
        orderMapper.insertOrder(orderRequest);
        List<String> productIds=orderRequest.getSelectedProducts();
        String userId=orderRequest.getUser_id();

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
            orderItem.setStatus("unpaid");
            orderItem.setShop_id(userId);
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


//        // 生成交易快照
//        TransactionSnapshot snapshot = new TransactionSnapshot();
//        snapshot.setSnapshotId(UUID.randomUUID().toString());
//        snapshot.setOrderId(orderId);
//        snapshot.setUserId(userId);
//        snapshot.setProductId(goodsList.get(0).getId()); // 示例取第一个商品ID
//        snapshot.setTotalPrice(totalPrice);
//        snapshotService.createSnapshot(snapshot);
//
//        return orderId;
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

    public void payOrder(String orderId) {
        orderMapper.payOrder(orderId);
    }


}
