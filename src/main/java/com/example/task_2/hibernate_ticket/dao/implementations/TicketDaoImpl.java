package com.example.task_2.hibernate_ticket.dao.implementations;

import com.example.task_2.hibernate_ticket.dao.TicketDAO;
import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@RequiredArgsConstructor
public class TicketDaoImpl implements TicketDAO {

    private final SessionFactory sessionFactory;


    @Override
    public Ticket getTicketById(Long ticketId) {
        Session session = sessionFactory.openSession();
        Ticket ticket = session.get(Ticket.class, ticketId);
        session.close();
        return ticket;
    }

    @Override
    public void saveTicket(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateTicketType(Long ticketId, TicketType newType) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, ticketId);
        ticket.setTicketType(newType);
        transaction.commit();
        session.close();
    }
}
