package com.example.task_2.hibernate_ticket.dao.implementations;

import com.example.task_2.hibernate_ticket.dao.UserDAO;
import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;
import com.example.task_2.hibernate_ticket.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@RequiredArgsConstructor
public class UserDaoImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Override
    public void deleteUserById(Long userId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(new User(userId));
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(Long userId) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, userId);
        session.close();
        return user;
    }

    @Override
    public List<Ticket> getUserTickets(Long userId) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, userId);
        List<Ticket> tickets = user.getTickets();
        System.out.println("Previous print statement: " + tickets.size()); // print statement just to load all child entities and do not make fetch EAGER
        session.close();
        return tickets;
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUserNameAndAllTicketTypes(Long userId, String newName, TicketType newType) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, userId);
        user.setName(newName);
        user.getTickets().forEach(ticket -> ticket.setTicketType(newType));
        transaction.commit();
        session.close();
    }
}
