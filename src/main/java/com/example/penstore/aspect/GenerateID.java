package com.example.penstore.aspect;

import com.example.penstore.dto.CartRequest;
import com.example.penstore.dto.GoodsRequest;
import com.example.penstore.dto.UserRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.After;

import java.util.UUID;

@Aspect
@Component
public class GenerateID{
    @Pointcut("execution(* com.example.penstore.service.UserService.insertUser(..))")
    public void insertUserPointcut(){}
    @Pointcut("execution(* com.example.penstore.service.CartService.addToCart(..))")
    public void addCartPointcut(){}
    @Pointcut("execution(* com.example.penstore.service.GoodsService.insertGoods(..))")
    public void addGoodsPointcut(){}

    //用户ID
    @Before("insertUserPointcut() && args(user)")
    public void generateUserID(UserRequest user) {
        user.setId(UUID.randomUUID().toString());
    }

    //购物车ID
    @Before("addCartPointcut() && args(cartRequest)")
    public void generateCartID(CartRequest cartRequest) {cartRequest.setId(UUID.randomUUID().toString());}

    //商品ID
    @Before("addGoodsPointcut() && args(goodsRequest)")
    public void generateGoodsID(GoodsRequest goodsRequest) {goodsRequest.setId(UUID.randomUUID().toString());}

    //订单生成uuid
    private static final ThreadLocal<String> orderIdThreadLocal = new ThreadLocal<>();
    @Pointcut("execution(* com.example.penstore.service.OrderService.createOrder(..))")
    public void createOrderPointcut(){}
    @Before("createOrderPointcut() && args(userId, address)")
    public void generateOrderID(String userId, String address) {
        // 这里可以通过 ThreadLocal 或其他方式将生成的 UUID 传递给 OrderService
        String orderId = UUID.randomUUID().toString();
        // 将生成的 orderId 存储到 ThreadLocal 或其他上下文中
        orderIdThreadLocal.set(orderId);

    }
    public static String getCurrentOrderId() {
        return orderIdThreadLocal.get();
    }

    // 清理 ThreadLocal，防止内存泄漏
    public static void clear() {
        orderIdThreadLocal.remove();
    }
    @After("createOrderPointcut()")
    public void clearOrderId() {
        GenerateID.clear();
    }


}
