package com.example.penstore.dto;
import  java.util.Date;

public class CommentRequest {

        private String id;
        private String userId;
        private String pop;
        private String goodsId;
        private int star;
        private java.util.Date commentAt;
        private String images;
        private boolean isReplied;
        private String content;
        private String parentId;

        // Getter and Setter for id
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        // Getter and Setter for userId
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        // Getter and Setter for pop
        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        // Getter and Setter for goodsId
        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        // Getter and Setter for star
        public int getStar() {
            return star;
        }

        public void setStar(Integer star) {
            this.star = star;
        }

        // Getter and Setter for commentAt
        public java.util.Date getCommentAt() {
            return commentAt;
        }

        public void setCommentAt(java.util.Date commentAt) {
            this.commentAt = commentAt;
        }

        // Getter and Setter for images
        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        // Getter and Setter for isReplied
        public boolean getIsReplied() {
            return isReplied;
        }

        public void setIsReplied(boolean isReplied) {
            this.isReplied = isReplied;
        }

        // Getter and Setter for content
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        // Getter and Setter for parentId
        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }


}
