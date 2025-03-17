// TransactionSnapshotMapper.java
package com.example.penstore.dao;

import com.example.penstore.domain.TransactionSnapshot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// TransactionSnapshotMapper.java
@Mapper
public interface TransactionSnapshotMapper {
    // 用户维度：按订单ID查询
    void insertSnapshot(TransactionSnapshot snapshot);
    TransactionSnapshot findByOrderId(String orderId);

    // 商家维度：按商品+商家查询
    List<TransactionSnapshot> findByShopAndProduct(
            @Param("shopId") String shopId,
            @Param("productId") String productId
    );
    List<TransactionSnapshot> findByShopId(String shopId);
}