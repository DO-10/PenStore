package com.example.penstore.service;

import com.example.penstore.dao.CommentMapper;
import com.example.penstore.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    public void addComment(Comment comment) {
        // 后端自动生成ID已通过切面处理
//        System.out.println(comment.getGoodsId());
//        System.out.println(comment.getUserId());
//        System.out.println(comment.getStar());
//        System.out.println(comment.getContent());
        comment.setCommentAt(LocalDateTime.now());
        if ("2".equals(comment.getPop())) {
            commentMapper.updateReplyStatus(comment.getParentId());
        }
        commentMapper.insertComment(comment);
    }

    public List<Comment> getCommentsByGoodsId(String goodsId) {
        return commentMapper.selectByGoodsId(goodsId);
    }

    public List<Comment> getRepliesByParentId(String parentId) {
        return commentMapper.selectRepliesByParentId(parentId);
    }
}
