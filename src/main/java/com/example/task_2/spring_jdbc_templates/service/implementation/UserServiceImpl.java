package com.example.task_2.spring_jdbc_templates.service.implementation;

import com.example.task_2.spring_jdbc_templates.dao.AddressDao;
import com.example.task_2.spring_jdbc_templates.dao.UserDao;
import com.example.task_2.spring_jdbc_templates.entity.Address;
import com.example.task_2.spring_jdbc_templates.entity.User;
import com.example.task_2.spring_jdbc_templates.exception.OperationIsNotAvailableException;
import com.example.task_2.spring_jdbc_templates.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final AddressDao addressDao;

    @Value("${application.update.user}")
    private boolean isAvailableToUpdate;

    @Override
    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    @Transactional
    public void updateTicketNameAndSaveAddress(Long userId, String newName, Address newAddress) {
        if (!isAvailableToUpdate)
            throw new OperationIsNotAvailableException("User update name and save new address operation is not available!");

        userDao.updateUserName(userId, newName);
        addressDao.saveAddress(newAddress);
    }
}
