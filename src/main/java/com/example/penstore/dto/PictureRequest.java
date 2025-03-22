package com.example.penstore.dto;

import org.springframework.stereotype.Component;

@Component
public class PictureRequest {

    private String picture;
    private String picid;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicid() {
        return picid;
    }

    public void setPicid(String picid) {
        this.picid = picid;
    }
}
