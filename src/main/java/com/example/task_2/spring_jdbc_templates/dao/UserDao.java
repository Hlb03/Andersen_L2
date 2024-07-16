package com.example.task_2.spring_jdbc_templates.dao;

import com.example.task_2.spring_jdbc_templates.entity.User;

public interface UserDao {
    User getUserById(Long id);
    void updateUserName(Long userId, String newName);
}
