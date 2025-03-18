package com.example.penstore.dao;

import com.example.penstore.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 插入评论或回复
    void insertComment(Comment comment);
    void insertReply(Comment reply);

    // 更新评论的回复状态
    void updateReplyStatus( String parentId);

    // 根据商品ID查询评论
    List<Comment> selectByGoodsId(String goodsId);

    // 根据父评论ID查询回复
    List<Comment> selectRepliesByParentId(String parentId);

    Comment selectByParentId(String parentId);
}