// ChatController.java
package com.example.penstore.controller;

import com.example.penstore.domain.ChatMessage;
import com.example.penstore.domain.User;
import com.example.penstore.service.ChatService;
import com.example.penstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping("/{otherUserId}")
    public String chatPage(@PathVariable String otherUserId,
                           @SessionAttribute("user") User currentUser,
                           Model model) {
        User otherUser = userService.getById(otherUserId);
        List<ChatMessage> messages = chatService.getAllChatMessages(
                currentUser.getId(),
                otherUserId
        );
        chatService.markMessagesAsRead(currentUser.getId(), otherUserId);
        System.out.println("使得"+ messages.get(0).getCommentId());
        model.addAttribute("otherUser", otherUser);
        model.addAttribute("messages", messages);
        model.addAttribute("currentUser", currentUser);
        return "chat";
    }

}