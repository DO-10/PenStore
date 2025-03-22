package com.example.penstore.service;

import com.example.penstore.dao.UserMapper;
import com.example.penstore.domain.User;
import com.example.penstore.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    @Transactional
    public void insertUser(UserRequest user) {
        userMapper.newUser(user);
    }

    public User signin(UserRequest userRequest) {
        // 验证密码是否正确
        List<User> u = userMapper.a();
        System.out.println(u.get(0).getId());
        System.out.println(u.get(0).getEmail());
        System.out.println(u.get(0).getPassword());
        User user = userMapper.signin(userRequest);
        if (user == null) {
            return null;
        }
        return user;
    }
    @Transactional
    public boolean checkUsernameExists(String email){
        if(userMapper.checkEmailExists(email)){
            return true;
        }else{
            return false;
        }



    }

    public User getById(String id) {
        User user = userMapper.getById(id);
        return user;
    }

    public void updateUser(UserRequest userRequest) {userMapper.updateUser(userRequest);}

    //更新地址
    public void addAddress(String userId, String address) {
        userMapper.addAddress(userId,address);
    }


    public void deleteAddress(String userId, String address) {
        userMapper.deleteAddress(userId,address);
    }
}
