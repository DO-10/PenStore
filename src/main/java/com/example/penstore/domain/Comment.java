package com.example.penstore.domain;

import java.time.LocalDateTime;

public class Comment {
    private String id;
    private String userId;
    private String pop;
    private String goodsId;
    private Integer star;
    private LocalDateTime commentAt;
    private String images;
    private Boolean isReplied;
    private String content;
    private String parentId;
    public Comment() {}

    public String getPop() {
        return pop;
    }
    public void setCommentAt(LocalDateTime commentAt) {
        this.commentAt = commentAt;
    }
    public LocalDateTime getCommentAt() {
        return commentAt;
    }
    public String getParentId(){
        return parentId;
    }
    public void setParentId(String parentId){
        this.parentId = parentId;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public Integer getStar() {
        return star;
    }
    public void setStar(Integer star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

}
