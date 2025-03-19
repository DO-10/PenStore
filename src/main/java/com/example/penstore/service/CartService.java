package com.example.penstore.service;

import com.example.penstore.dao.CartMapper;
import com.example.penstore.domain.Goods;
import com.example.penstore.dto.CartRequest;
import com.example.penstore.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    @Transactional
    public List<Goods> getGoodsListByUserId(UserRequest userRequest) {
        List<Goods> GoodsList= cartMapper.getCartItemsByUserId(userRequest);
        return GoodsList;
    }

    public BigDecimal calculateTotalPriceByUserId(String userId) {
        List<Goods> cartItems = cartMapper.getChosenCartItemsByUserId(userId);

        // 使用 Stream 计算总价格
        return cartItems.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity()))) // 直接使用 BigDecimal 构造函数
                .reduce(BigDecimal.ZERO, BigDecimal::add); // 累加所有商品的总价
    }

    public void addToCart(CartRequest cartRequest) {
        //查找用户是否添加过
        if(cartMapper.isAdded(cartRequest))
            cartMapper.increase(cartRequest);
        else //商品第一次被添加进购物车
            cartMapper.addToCart(cartRequest);
    }


    //修改数量
    public int updateCart(String userId, String goodsId, String operation) {
        // 根据操作类型更新购物车
        switch (operation) {
            case "increase":
            case "decrease":
                cartMapper.updateQuantity(userId, goodsId, operation);
                return cartMapper.getProductQuantity(userId, goodsId);

            case "delete":
                cartMapper.deleteProduct(userId, goodsId);
                return 0;
            case "choose":
            case "unchoose":
                cartMapper.updateIsChosen(userId, goodsId, operation.equals("choose"));
                return cartMapper.getProductQuantity(userId, goodsId);
        }
        return cartMapper.getProductQuantity(userId, goodsId);

    }

    public BigDecimal calculateTotalPrice(String userId) {
        return cartMapper.calculateTotalPrice(userId);
    }

    public boolean isAllChosen(String userId) {
        return cartMapper.isAllChosen(userId);
    }

}
