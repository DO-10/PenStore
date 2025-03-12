package com.example.penstore.domain;

import org.springframework.stereotype.Component;

@Component
public class Order {
    private String id;
    private String createdAt;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String cratedAt) {
        this.createdAt = cratedAt;
    }
}
