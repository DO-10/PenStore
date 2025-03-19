package com.example.penstore.dto;
import com.example.penstore.domain.Goods;

import java.util.List;

public class OrderRequest {
    private String order_id;
    private String user_id;
    private String username;
    private String name;
    private String goods_id;
    private String shipping_address;
    private String payment_status;
    private String order_status;
    private String tracking_number;
    private java.time.LocalDateTime created_at;
    private String updated_at;
    private String order_date;
    private String phone;
    private String order_items_id;
    private String note;
    private String amount;
    private String images;
    private String delivery_time;
    private int number;
    private String startDate;
    private String endDate;
    private String goodsName;
    private String shop_id;                   //封装店铺id
    private String status;                    //传次导航参数
    private String address;
    private String existingAddress;
    private String newAddress;
    private String addressType;
    private List<String> selectedProducts;
    private List<Goods> goodsList;

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public  String getOrderId() {
        return order_id;
    }
    public void setOrderId(String order_id) {
        this.order_id = order_id;
    }

    public String getUserId() {
        return user_id;
    }
    public void setUserId(String user_id) {
        this.user_id = user_id;
    }
    public String getUserName() {
        return username;
    }
    public void setUserName(String username) {
        this.username = username;
    }
    public  String getNewAddress(){
        return newAddress;
    }
    public void setNewAddress(String newAddress){
        this.newAddress = newAddress;
    }
    public String getAddressType(){
        return addressType;
    }
    public void setAddressType(String addressType){
        this.addressType = addressType;
    }
    public List<String> getSelectedProducts() {
        return selectedProducts;
    }
    public void setSelectedProducts(List<String> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
    public OrderRequest() {
    }

    public String getOrder_items_id(){
        return order_items_id;
    }
    public void setOrder_items_id(String order_items_id){
        this.order_items_id = order_items_id;
    }
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
    public java.time.LocalDateTime getCreated_at() {
        return created_at;
    }
    public void setCreated_at(java.time.LocalDateTime created_at) {
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
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public String getDelivery_time() {
        return delivery_time;
    }
    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getShop_id() {
        return shop_id;
    }
    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getExistingAddress() {
        return existingAddress;
    }

    public void setExistingAddress(String existingAddress) {
        this.existingAddress = existingAddress;
    }
}
