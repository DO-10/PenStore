package com.example.penstore.service;

import com.example.penstore.dao.GoodsMapper;
import com.example.penstore.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getByCategory(String category_id) {
        return goodsMapper.getByCategory(category_id);
    }

    public Goods getById(String id) {
        return goodsMapper.getById(id);
    }

    public List<Goods> getByInput(String query) {
        return goodsMapper.getByInput(query);
    }

    public List<Goods> searchProducts(String query) {
        return goodsMapper.searchProducts(query);
    }
    public List<Goods> getProductsWithCartQuantities(String[] productIds) {
        // 调用Mapper获取商品信息
        return goodsMapper.getProductsByIds(productIds);
    }
}
