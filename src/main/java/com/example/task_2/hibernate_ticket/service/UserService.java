package com.example.task_2.hibernate_ticket.service;

import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;
import com.example.task_2.hibernate_ticket.entity.User;

import java.util.List;

public interface UserService {
    void deleteTicketById(Long ticketId);
    User getUserById(Long userId);
    List<Ticket> getUserTickets(Long userId);
    void saveUser(User user);
    void updateUserNameAndAllTicketTypes(Long userId, String newName, TicketType newType);
}
