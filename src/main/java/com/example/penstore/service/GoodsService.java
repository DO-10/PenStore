package com.example.penstore.service;

import com.example.penstore.common.CommonResponse;
import com.example.penstore.dto.GoodsRequest;
import com.example.penstore.entity.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> getByCategory(String category_id);

    Goods getById(String id);

    List<Goods> getByInput(String query);

    List<Goods> searchProducts(String query);

    List<Goods> getProductsWithCartQuantities(String[] productIds);

    void insertGoods(GoodsRequest goodsRequest);

    List<Goods> getGoodsByShopId(String shop_id);

    List<Goods> getGoodsByQuery(GoodsRequest goodsRequest);

    List<Goods> getGoodsByStatus(String status, String shop_id);

    void updateGoods(String id, String operation, String shop_id);

    void newGoods(GoodsRequest goodsRequest);

    void updateStock(String id, int stock);
}