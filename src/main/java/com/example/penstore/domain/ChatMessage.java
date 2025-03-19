package com.example.penstore.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    public String getSender_id() { return senderId; }
    public void setSender_id(String senderId) { this.senderId = senderId; }

    public String getReceiver_id() { return receiverId; }
    public void setReceiver_id(String receiverId) { this.receiverId = receiverId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public boolean getIs_read() { return isRead; }
    public void setIs_read(boolean read) { isRead = read; }
}