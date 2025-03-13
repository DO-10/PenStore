package com.example.penstore.dao;

import com.example.penstore.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 插入评论或回复
    void insertComment(Comment comment);

    // 更新评论的回复状态
    void updateReplyStatus(@Param("parentId") String parentId);

    // 根据商品ID查询评论
    List<Comment> selectByGoodsId(@Param("goodsId") String goodsId);

    // 根据父评论ID查询回复
    List<Comment> selectRepliesByParentId(@Param("parentId") String parentId);
}