package com.example.penstore.domain;

import org.springframework.stereotype.Component;

@Component
public class Goods {
    private String id;
    private String name;
    private String description;
    private java.math.BigDecimal price;
    private int stock;
    private String imageUrl;
    private String quantity;

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
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
