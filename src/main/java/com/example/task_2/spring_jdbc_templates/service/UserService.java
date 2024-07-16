package com.example.task_2.spring_jdbc_templates.service;

import com.example.task_2.spring_jdbc_templates.entity.Address;
import com.example.task_2.spring_jdbc_templates.entity.User;

public interface UserService {
    User getUserById(Long userId);
    void updateTicketNameAndSaveAddress(Long userId, String newName, Address newAddress);
}
