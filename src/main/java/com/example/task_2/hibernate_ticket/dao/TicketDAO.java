package com.example.task_2.hibernate_ticket.dao;

import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;

public interface TicketDAO {
    Ticket getTicketById(Long ticketId);
    void saveTicket(Ticket ticket);
    void updateTicketType(Long ticketId, TicketType newType);
}
