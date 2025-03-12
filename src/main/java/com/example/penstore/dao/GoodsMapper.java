package com.example.penstore.dao;

import com.example.penstore.domain.Goods;
import com.example.penstore.dto.GoodsRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<Goods> getByCategory(String category_id);
    Goods getById(String id);
    List<Goods> getByInput(String query);
    List<Goods> searchProducts(String query);
    List<Goods> getProductsByIds(String[] productIds);
    void insertGoods(GoodsRequest goodsRequest);
}
