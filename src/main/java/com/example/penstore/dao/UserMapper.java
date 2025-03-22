package com.example.penstore.dao;

import com.example.penstore.domain.User;
import com.example.penstore.dto.UserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    //User findByEmail(@Param("email") String email);

    void newUser(UserRequest user);

    User signin(UserRequest user);



    boolean checkEmailExists(String email);
    List<User> a();

    User getById(String id);
    void updateUser(UserRequest userRequest);
    void addAddress(String userId, String address);
}

