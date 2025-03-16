// TransactionSnapshot.java
package com.example.penstore.entity;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class TransactionSnapshot {
    private String snapshotId;
    private String orderId;
    private String userId;
    private String username;
    private String productId;

    private String shopId=userId;
    private BigDecimal price; // 商品单价
    private int quantity;    // 商品数量

    private BigDecimal totalPrice;
    private String createdAt;

    // Getters and Setters
    public String getSnapshotId() { return snapshotId; }
    public void setSnapshotId(String snapshotId) { this.snapshotId = snapshotId; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    // Getters and Setters（新增字段）
    public String getShopId() { return shopId; }
    public void setShopId(String shopId) { this.shopId = shopId; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}