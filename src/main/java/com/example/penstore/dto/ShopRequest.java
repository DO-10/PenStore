package com.example.penstore.dto;

import org.springframework.stereotype.Component;

@Component
public class ShopRequest {


    private String shopname;
    private String shopphone;
    private String salesman;
    private String introduction;
    private String license;
    private String shopid;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }
    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public ShopRequest()
    {

    }
    public ShopRequest(String shopid, String shopname, String shopphone, String salesman, String introduction, String license, String status,String method) {
        this.shopid = shopid;
        this.shopname = shopname;
        this.shopphone = shopphone;
        this.salesman = salesman;
        this.introduction = introduction;
        this.license = license;
        this.status = status;
        this.method=method;
    }
    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopphone() {
        return shopphone;
    }

    public void setShopphone(String shopphone) {
        this.shopphone = shopphone;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
