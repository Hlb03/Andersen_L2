package com.example.task_2.spring_boot.service;

import com.example.task_2.spring_boot.entity.Ticket;
import com.example.task_2.spring_boot.entity.User;

import java.util.List;

public interface TicketService {
    void deleteTicket(Long ticketId);
    Ticket getTicketById(Long ticketId);
    List<Ticket> getUserTicket(User user);
    void createTicket(Ticket ticket);
    void updateTicketById(Long ticketId, Ticket ticket);
}
