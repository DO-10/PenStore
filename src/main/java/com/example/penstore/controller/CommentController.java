package com.example.penstore.controller;

import com.example.penstore.service.impl.ChatService;
import com.example.penstore.service.impl.GoodsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.penstore.entity.Comment;
import com.example.penstore.service.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.penstore.entity.ChatMessage;
import java.util.List;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    //提交评论
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        commentService.addComment(comment);

        // 同时生成一条聊天消息
        ChatMessage message = new ChatMessage();
        message.setContent(comment.getContent());
        message.setReceiverId(goodsServiceImpl.getById(comment.getGoodsId()).getShop_id());
        message.setSenderId(comment.getUser_id());
        message.setCommentId("goodsId=" + comment.getGoodsId() + "&parentId=" + comment.getId());
        message.setTimestamp(LocalDateTime.now());
        chatService.sendMessage(message);

        return ResponseEntity.ok("评论成功");
    }

    //提交回复
    @PostMapping("/reply")
    public ResponseEntity<?> createReply(@RequestBody Comment reply) {
        commentService.addReply(reply);
        return ResponseEntity.ok("回复成功");
    }

    //获取指定商品下的所有评论
    @GetMapping("/goods/{goodsId}")
    public ResponseEntity<List<Comment>> getCommentsByGoodsId(@PathVariable String goodsId) {
        List<Comment> comments = commentService.getNestedComments(goodsId);
        return ResponseEntity.ok(comments);
    }

    // 获取指定评论的父评论（用于回复显示）
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<Comment> getParentComment(@PathVariable String parentId) {
        Comment parentComment = commentService.selectByParentId(parentId);
        if (parentComment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parentComment);
    }
}