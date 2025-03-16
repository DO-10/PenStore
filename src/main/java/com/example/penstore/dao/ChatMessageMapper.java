package com.example.penstore.dao;

import com.example.penstore.entity.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatMessageMapper {
    // 最后一条消息（返回Map包含content和timestamp）
    Map<String, Object> selectLastMessage(
            String senderId, String receiverId
    );

    // 未读消息计数
    int countUnreadMessages(
            String senderId,
            String receiverId
    );

    // 完整聊天记录（返回实体对象列表）
    List<ChatMessage> selectAllChatMessages(
            String senderId, String receiverId
    );
    void insertMessage(ChatMessage message);  // 插入新消息
    void markAsRead(String senderId, String receiverId); // 标记消息为已读
}
