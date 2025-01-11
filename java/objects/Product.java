package objects;

import java.math.BigDecimal;

public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock; // 新增的 stock 属性
    private String imageUrl; // 新增的 imageUrl 属性
    private String quantity;

    // Getter 和 Setter 方法
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
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getStock() { // Getter for stock
        return stock;
    }
    public void setStock(int stock) { // Setter for stock
        this.stock = stock;
    }
    public String getImageUrl() { // 新增的 Getter
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) { // 新增的 Setter
        this.imageUrl = imageUrl;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
