package com.example.penstore.dto;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class GoodsRequest {
    private String id;
    private String name;
    private String description;
    private java.math.BigDecimal price;
    private int stock;
    private String image_url;
    private String quantity;
    private String seller;
    private String category_id;
    private String status;
    private String shop_id;
    private String startPrice;
    private String endPrice;
    private String category;//封装搜索商品种类
    private MultipartFile image;
    public GoodsRequest() {  }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name=name; }

    public String getDescription() {return description; }

    public void setDescription(String description) { this.description=description; }

    public java.math.BigDecimal getPrice() { return price;}

    public void setPrice(java.math.BigDecimal price) { this.price=price; }

    public int getStock() {return stock; }

    public void setStock(int stock) { this.stock=stock; }

    public String getImageUrl() { return image_url;}

    public void setImageUrl(String image_url) { this.image_url=image_url; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity=quantity; }
    public String getSeller() { return seller; }
    public void setSeller(String seller) { this.seller=seller; }
    public String getCategory_id() { return category_id; }
    public void setCategory_id(String category_id) { this.category_id=category_id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status=status; }
    public String getShop_id() { return shop_id; }
    public void setShop_id(String shop_id) { this.shop_id=shop_id; }
    public String getStartPrice() { return startPrice; }
    public void setStartPrice(String startPrice) { this.startPrice=startPrice; }
    public String getEndPrice() { return endPrice; }
    public void setEndPrice(String endPrice) { this.endPrice=endPrice; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category=category; }
    public MultipartFile getImage() { return image; }
    public void setImage(MultipartFile image) { this.image=image; }
}
