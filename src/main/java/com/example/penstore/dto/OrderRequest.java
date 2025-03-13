package com.example.penstore.dto;

public class OrderRequest {
    private String userid;
    private String address;
    private String orderId;
    public String getUserid() {return userid;}
    public String getAddress() {return address;}
    public String getOrderId() {return orderId;}
    public void setUserid(String userid) {this.userid = userid;}
    public void setAddress(String address) {this.address = address;}
    public void setOrderId(String orderId) {this.orderId = orderId;}
}
