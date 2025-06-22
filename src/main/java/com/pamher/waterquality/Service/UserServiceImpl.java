package com.pamher.waterquality.Service;

import com.pamher.waterquality.mapper.UserMapper;
import com.pamher.waterquality.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //根据用户名查找
    @Override
    public Optional<User> findByUsername(String username) {

        Optional<User> user = userMapper.findByUsername(username);

        return user;
    }
//保存用户
    @Override
    public void saveUser(User user)
    {
        userMapper.saveUser(user);
    }

    //获取所有用户
    @Override
    public List<User> getAllUsers()
    {
        return userMapper.getAllUsers();
    }
    //根据ID查询
    @Override
    public Optional<User> findById(Integer userId) {

        return userMapper.findById(userId);
    }
}
