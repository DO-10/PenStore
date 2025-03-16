package com.example.penstore.service;

import com.example.penstore.dao.ChatMessageMapper;
import com.example.penstore.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {
    private final ChatMessageMapper chatMessageMapper;

    @Autowired
    public ChatService(ChatMessageMapper chatMessageMapper) {
        this.chatMessageMapper = chatMessageMapper;
    }

    public List<ChatMessage> getAllChatMessages(String myUserId, String otherUserId) {
        System.out.println("正在获取所有聊天记录"+myUserId+" "+otherUserId);
        List<ChatMessage> messages = chatMessageMapper.selectAllChatMessages(myUserId, otherUserId);
        return messages;
    }

    public int getUnreadCount(String myUserId, String otherUserId) {
        return chatMessageMapper.countUnreadMessages(myUserId, otherUserId);
    }

    public Map<String, Object> getLastMessage(String myUserId, String otherUserId) {
        return chatMessageMapper.selectLastMessage(myUserId, otherUserId);
    }

    @Transactional
    public void markMessagesAsRead(String myUserId, String otherUserId) {
        chatMessageMapper.markAsRead(myUserId, otherUserId);
    }
    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(ChatMessage message) {
        chatMessageMapper.insertMessage(message);
    }
}
