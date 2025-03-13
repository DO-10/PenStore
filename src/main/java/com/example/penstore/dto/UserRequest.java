package com.example.penstore.dto;

import org.springframework.stereotype.Component;

@Component
public class UserRequest {
    private String id;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String username;
    private String verificationCode;

    public UserRequest() {}  // 必须有

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
