package com.example.task_2.hibernate_ticket.service.implementations;

import com.example.task_2.hibernate_ticket.dao.UserDAO;
import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;
import com.example.task_2.hibernate_ticket.entity.User;
import com.example.task_2.hibernate_ticket.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public void deleteTicketById(Long ticketId) {
        userDAO.deleteUserById(ticketId);
    }

    @Override
    public User getUserById(Long userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public List<Ticket> getUserTickets(Long userId) {
        return userDAO.getUserTickets(userId);
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public void updateUserNameAndAllTicketTypes(Long userId, String newName, TicketType newType) {
        userDAO.updateUserNameAndAllTicketTypes(userId, newName, newType);
    }
}
