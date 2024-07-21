package com.example.task_2.spring_boot.service.implementation;

import com.example.task_2.spring_boot.entity.Ticket;
import com.example.task_2.spring_boot.entity.User;
import com.example.task_2.spring_boot.repository.TicketRepository;
import com.example.task_2.spring_boot.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public void deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.getReferenceById(ticketId);
    }

    @Override
    public List<Ticket> getUserTicket(User user) {
        return ticketRepository.getTicketByUser(user);
    }

    @Override
    public void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void updateTicketById(Long ticketId, Ticket ticket) {
        Ticket storedTicket = ticketRepository.getReferenceById(ticketId);
        storedTicket.updateTicket(ticket);
    }
}
