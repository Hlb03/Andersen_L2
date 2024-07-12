package com.example.task_2.jdbc_ticket.dao;

import com.example.task_2.jdbc_ticket.entity.User;

public interface UserDAO {
    void deleteUser(Long userId);
    User getUserById(Long userId);
    void saveUser(User user);
}
