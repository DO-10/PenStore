// TransactionSnapshotService.java
package com.example.penstore.service;

import com.example.penstore.dao.TransactionSnapshotMapper;
import com.example.penstore.domain.TransactionSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionSnapshotService {
    @Autowired
    private TransactionSnapshotMapper snapshotMapper;

    // 创建交易快照（需确保从 Goods 中提取商家ID）
    public void createSnapshot(TransactionSnapshot snapshot) {
        snapshotMapper.createSnapshot(snapshot);
    }

    // 用户视角：通过订单ID获取单个快照（需校验用户权限）
    public TransactionSnapshot getSnapshotByOrderId(String orderId) {
        return snapshotMapper.findByOrderId(orderId);
    }

    // 商家视角：通过商家ID和商品ID获取相关快照列表
    public List<TransactionSnapshot> getSnapshotsByShopAndProduct(String shopId, String productId) {
        return snapshotMapper.findByShopAndProduct(shopId, productId);
    }

    // 商家视角：通过商家ID获取所有商品快照（可选扩展）
    public List<TransactionSnapshot> getSnapshotsByShopId(String shopId) {
        return snapshotMapper.findByShopId(shopId);
    }
}