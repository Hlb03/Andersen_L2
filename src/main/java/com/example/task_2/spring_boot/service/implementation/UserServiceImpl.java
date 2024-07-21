package com.example.task_2.spring_boot.service.implementation;

import com.example.task_2.spring_boot.entity.User;
import com.example.task_2.spring_boot.repository.UserRepository;
import com.example.task_2.spring_boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.getReferenceById(userId);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUserName(Long userId, String newName) {
        User storedUser = userRepository.getReferenceById(userId);
        storedUser.setName(newName);
    }
}
