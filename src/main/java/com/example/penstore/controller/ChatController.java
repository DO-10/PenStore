// ChatController.java
package com.example.penstore.controller;

import com.example.penstore.entity.ChatMessage;
import com.example.penstore.entity.User;
import com.example.penstore.service.impl.ChatService;
import com.example.penstore.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping("/{currentUserId}/{otherUserId}")
    public ResponseEntity<?> chatPage(@PathVariable String otherUserId,
                                   @PathVariable String currentUserId) {
        User otherUser = userService.getById(otherUserId);
        if(otherUser==null){
            return ResponseEntity.badRequest().body("用户不存在");
        }
        List<ChatMessage> messages = chatService.getAllChatMessages(currentUserId, otherUserId);
        chatService.markMessagesAsRead(currentUserId, otherUserId);

        return ResponseEntity.ok(messages);  // 前端拿到 JSON 渲染
    }

}