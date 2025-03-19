package com.example.penstore.controller;

import com.example.penstore.service.ChatService;
import com.example.penstore.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.penstore.domain.Comment;
import com.example.penstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.penstore.domain.ChatMessage;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private  ChatMessage message;
    @Autowired
    private GoodsService goodsService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        message.setContent(comment.getContent());
        message.setReceiverId(goodsService.getById(comment.getGoodsId()).getShop_id());
        message.setSenderId(comment.getUser_id());
        message.setCommentId(comment.getId());
        message.setTimestamp(LocalDateTime.now());
        chatService.sendMessage(message);




        return ResponseEntity.ok().build();
    }
    @GetMapping("/submit")
    public String showCommentPage(
            @RequestParam String goodsId,
            Model model
    ) {
        model.addAttribute("goodsId", goodsId);
        return "comment";
    }
    // 提交回复
    @GetMapping("/reply")
    public String replyPage(
            @RequestParam String goodsId,     // 商品ID
            @RequestParam String parentId,    // 父评论ID
            Model model
    ) {

        Comment parentComment = commentService.selectByParentId(parentId);
        // 根据 goodsId 关联商品（确保回复属于正确商品）
        model.addAttribute("goodsId", goodsId);
        model.addAttribute("parentComment", parentComment);
        return "reply";
    }
    @PostMapping("/reply/submit")
    public ResponseEntity<?> createReply(@RequestBody Comment reply) {
        commentService.addReply(reply); // 调用 Service 层处理回复

        return ResponseEntity.ok().build();
    }



//    @GetMapping("/goods/{goodsId}")
//    public ResponseEntity<List<Comment>> getCommentsByGoodsId(@PathVariable String goodsId) {
//        return ResponseEntity.ok(commentService.getCommentsByGoodsId(goodsId));
//    }
//
//    @GetMapping("/replies/{parentId}")
//    public ResponseEntity<List<Comment>> getRepliesByParentId(@PathVariable String parentId) {
//        return ResponseEntity.ok(commentService.getRepliesByParentId(parentId));
//    }
}