package com.example.task_2.hibernate_ticket.service.implementations;

import com.example.task_2.hibernate_ticket.dao.TicketDAO;
import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;
import com.example.task_2.hibernate_ticket.service.TicketService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDAO ticketDAO;

    @Override
    public Ticket getTicketById(Long ticketId) {
        return ticketDAO.getTicketById(ticketId);
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketDAO.saveTicket(ticket);
    }

    @Override
    public void updateTicketType(Long ticketId, TicketType newType) {
        ticketDAO.updateTicketType(ticketId, newType);
    }
}
