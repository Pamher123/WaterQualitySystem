package com.pamher.waterquality.Service;

import com.pamher.waterquality.pojo.Device;
import com.pamher.waterquality.pojo.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
    void saveUser(User user);
    Optional<User> findById(Integer userId);
    List<User> getAllUsers();

}
