package com.example.task_2.jdbc_ticket.dao;

import com.example.task_2.jdbc_ticket.entity.Ticket;
import com.example.task_2.jdbc_ticket.entity.TicketType;

import java.util.List;

public interface TicketDAO {
    Ticket getTicketById(Long ticketId);
    List<Ticket> getTicketsByUserId(Long userId);
    void saveTicket(Ticket ticket);
    void updateTicketType(Long ticketId, TicketType newType);

    void updateTicketTypeAndUserName(Long ticketId, TicketType newType, Long userId, String newName);
}
