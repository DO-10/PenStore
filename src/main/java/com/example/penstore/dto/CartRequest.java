package com.example.penstore.dto;

public class CartRequest {
    private String id;
    private String user_id;
    private String product_id;
    private String added_at;
    private boolean ischosen;
    private String quantity;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public String getAdded_at() {
        return added_at;
    }
    public void setAdded_at(String added_at) {
        this.added_at = added_at;
    }
    public boolean getIschosen() {
        return ischosen;
    }
    public void setIschosen(boolean ischosen) {
        this.ischosen = ischosen;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
