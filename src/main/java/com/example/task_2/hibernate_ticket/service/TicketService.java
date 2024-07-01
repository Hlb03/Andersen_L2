package com.example.task_2.hibernate_ticket.service;

import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;

public interface TicketService {

    Ticket getTicketById(Long ticketId);
    void saveTicket(Ticket ticket);
    void updateTicketType(Long ticketId, TicketType newType);
}
