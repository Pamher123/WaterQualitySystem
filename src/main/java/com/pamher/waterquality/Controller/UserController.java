package com.pamher.waterquality.Controller;

import com.pamher.waterquality.Service.UserService;
import com.pamher.waterquality.pojo.Device;
import com.pamher.waterquality.pojo.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080/")
public class UserController {

    //使用构造器注入
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {


            Optional<User> existingUser = userService.findByUsername(user.getUsername());
            if (existingUser.isPresent()) {

                return ResponseEntity.badRequest().body("用户名已存在！");
            }

            user.setUserId(null);


            userService.saveUser(user);


            return ResponseEntity.ok("注册成功！");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body("注册失败：" + e.getMessage());
        }
    }
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        Optional<User> userOptional = userService.findByUsername(user.getUsername());
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(user.getPassword())) {
            User foundUser = userOptional.get();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "登录成功！");
            response.put("userId", foundUser.getUserId());
            response.put("username", foundUser.getUsername());
            response.put("isRoot", foundUser.getIsRoot());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("用户名或密码错误！");
    }
    /**
     * 拥有 root 权限的用户修改普通用户信息
     */
    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@RequestParam Integer rootUserId, @PathVariable Integer userId,
                                             @RequestBody User updatedUser) {
        Optional<User> rootUser = userService.findById(rootUserId);
        if (rootUser.isEmpty() || !rootUser.get().getIsRoot()) {
            // 使用 getRoot() 访问 isRoot
            return ResponseEntity.status(403).body("无权限操作！");
        }

        Optional<User> targetUser = userService.findById(userId);
        if (targetUser.isEmpty()) {
            return ResponseEntity.badRequest().body("目标用户不存在！");
        }

        User existingUser = targetUser.get();
        if (existingUser.getIsRoot()) { // 使用 getRoot() 访问 isRoot
            return ResponseEntity.status(403).body("无法修改其他 root 用户的信息！");
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        userService.saveUser(existingUser);

        return ResponseEntity.ok("用户信息修改成功！");
    }
    //获取所有用户
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("获取用户列表失败", e);
            return ResponseEntity.status(500).body(null);
        }
    }



}
