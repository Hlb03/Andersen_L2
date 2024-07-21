package com.example.task_2.spring_boot.service;

import com.example.task_2.spring_boot.entity.User;

public interface UserService {
    User getUserById(Long userId);
    void deleteUser(Long userId);
    void createUser(User user);
    void updateUserName(Long userId, String newName);
}
