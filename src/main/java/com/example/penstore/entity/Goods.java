package com.example.penstore.entity;

import org.springframework.stereotype.Component;

@Component
public class Goods {
    private String id;
    private String name;
    private String description;
    private java.math.BigDecimal price;
    private int stock;
    private String image_url;
    private String quantity;
    private int sales;
    private String createTime;
    private String saleTime;
    private String status;
    private String shopId;
    private String categoryId;
    private String updateTime;
    private String category;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public java.math.BigDecimal getPrice() {
        return price;
    }
    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public int getSales() {
        return sales;
    }
    public void setSales(int sales) {
        this.sales = sales;
    }
    public String getCreate_time() {
        return createTime;
    }
    public void setCreate_time(String createTime) {
        this.createTime = createTime;
    }
    public String getSaleTime() {
        return saleTime;
    }
    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getShop_id() {
        return shopId;
    }
    public void setShop_id(String shopId) {
        this.shopId = shopId;
    }
    public String getCategory_id() {
        return categoryId;
    }
    public void setCategory_id(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getUpdate_time() {
        return updateTime;
    }
    public void setUpdate_time(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

}
