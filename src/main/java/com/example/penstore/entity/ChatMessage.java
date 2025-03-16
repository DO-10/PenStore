package com.example.penstore.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 聊天消息实体（纯Java对象版本）
 */
@Component
public class ChatMessage {

    private Long id;             // 消息ID（需要自己维护唯一性）
    private String senderId;       // 发送者ID
    private String receiverId;     // 接收者ID
    private String content;      // 消息内容
    private LocalDateTime timestamp; // 发送时间
    private boolean isRead;      // 是否已读

    // 无参构造方法（必须）
    public ChatMessage() {
    }

    // Getter & Setter 方法
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getReceiverId() { return receiverId; }
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public boolean getIsRead() { return isRead; }
    public void setIsRead(boolean read) { isRead = read; }
}