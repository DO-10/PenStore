package com.example.penstore.domain;

import org.springframework.stereotype.Component;

@Component
public class Order {
    private String order_id;
    private String user_id;
    private String username;
    private String name;//商品名称
    private String shipping_address;
    private String payment_status;
    private String order_status;
    private String tracking_number;
    private String created_at;
    private String updated_at;
    private String order_date;
    private String phone;
    private String note;
    private String amount;
    private String image_url;
    private String delivery_time;
    private String status;
    private int quantity;
    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShipping_address() {
        return shipping_address;
    }
    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }
    public String getPayment_status() {
        return payment_status;
    }
    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
    public String getOrder_status() {
        return order_status;
    }
    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
    public String getTracking_number() {
        return tracking_number;
    }
    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }
    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
    public String getOrder_date() {
        return order_date;
    }
    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public String getDelivery_time() {
        return delivery_time;
    }
    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
