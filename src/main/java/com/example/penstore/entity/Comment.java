package com.example.penstore.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class Comment {
    private String id;
    private String user_id;
    private String pop;
    private String goodsId;
    private Integer star;
    private LocalDateTime comment_at;
    private String time;
    private String images;
    private Boolean isReplied;
    private String content;
    private String parentId;
    private List<Comment> replies;
    private String username;
    public Comment() {}

    public String getPop() {
        return pop;
    }
    public void setPop(String pop) {
        this.pop = pop;
    }
    public void setComment_at(LocalDateTime comment_at) {
        this.comment_at = comment_at;
    }
    public LocalDateTime getComment_at() {
        return comment_at;
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
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
    public List<Comment> getReplies() {
        return replies;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public String getUsername(){
        return username;
    }



}
