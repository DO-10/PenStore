package com.example.penstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.penstore.domain.Comment;
import com.example.penstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
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
    // 提交评论



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