package com.pamher.waterquality.mapper;

import com.pamher.waterquality.pojo.User;

import java.util.List;
import java.util.Optional;

public interface UserMapper {

   //根据用户名查找用户
    Optional<User> findByUsername(String username);

   // 根据用户 ID 查找用户
    Optional<User> findById(Integer userId);

    //保存用户
    void saveUser(User user);

   //更新用户信息
    void updateUser(User user);

    //查询用户的 root 状态
    Boolean getUserRootStatus(Integer userId);

    List<User> getAllUsers();
}
