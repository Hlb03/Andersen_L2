package com.example.task_2.hibernate_ticket.dao;

import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;
import com.example.task_2.hibernate_ticket.entity.User;

import java.util.List;

public interface UserDAO {
    void deleteUserById(Long userId);
    User getUserById(Long userId);
    List<Ticket> getUserTickets(Long userId);
    void saveUser(User user);
    void updateUserNameAndAllTicketTypes(Long userId, String newName, TicketType newType);
}
