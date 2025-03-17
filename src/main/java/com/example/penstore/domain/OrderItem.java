package com.example.penstore.domain;
import java.math.BigDecimal;

public class OrderItem {
    // 订单项唯一标识（可根据实际需求决定是否需要）
    private String order_items_id;

    // 关联的订单ID
    private String order_id;

    // 商品ID
    private String product_id;

    // 购买数量
    private int quantity;

    // 商品单价（使用BigDecimal处理精确计算）
    private BigDecimal price;


    public String getOrder_items_id() {
        return order_items_id;
    }
    public void setOrder_items_id(String order_items_id) {
        this.order_items_id = order_items_id;
    }
    // Getter & Setter
    public String getId() {
        return order_items_id;
    }

    public void setId(String order_items_id) {
        this.order_items_id = order_items_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}