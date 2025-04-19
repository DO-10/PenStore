package com.example.penstore.service.impl;

import com.example.penstore.dao.ChatMessageMapper;
import com.example.penstore.dao.UserMapper;
import com.example.penstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerListService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ChatMessageMapper chatMessageMapper;

    // 获取所有用户 ID 列表
    public List<User> getUsers() {  //获取所有用户
        return userMapper.a();
    }
    //获取最后一条新消息
    public List<Map<String, Object>> getLastMessage(List<User> users, String myUserId) {
        List<Map<String, Object>> list = new ArrayList<>(); // 初始化列表
        for (User user : users) {
            // 调用Mapper方法获取结果，并添加到列表
            Map<String, Object> message = chatMessageMapper.selectLastMessage(user.getId(), myUserId);
            list.add(message);
        }
        return list;
    }

}
