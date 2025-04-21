package com.example.penstore.dao;

import com.example.penstore.entity.Goods;
import com.example.penstore.dto.CartRequest;
import com.example.penstore.dto.UserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.math.BigDecimal;
@Mapper
public interface CartMapper {


    List<Goods> getCartItemsByUserId(UserRequest userRequest);
    List<Goods> getCartItemsByUserId(String userId);

    void addToCart(CartRequest cartRequest);
    List<Goods> getChosenCartItemsByUserId(String userId);

    boolean isAdded(CartRequest cartRequest);

    void increase(CartRequest cartRequest);

    //数量
    void updateQuantity(String userId,
                        String goodsId,
                        String operation);

    void deleteProduct( String userId,
                       String goodsId);

    void updateIsChosen(String userId,
                         String goodsId,
                        boolean isChosen);

    int getProductQuantity( String userId,
                           String goodsId);

    BigDecimal calculateTotalPrice(String userId);

    boolean isAllChosen(String userId);


}