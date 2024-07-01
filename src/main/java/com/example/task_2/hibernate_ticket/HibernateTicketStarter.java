package com.example.task_2.hibernate_ticket;

import com.example.task_2.db_migration.FlywayConfig;
import com.example.task_2.hibernate_ticket.dao.TicketDAO;
import com.example.task_2.hibernate_ticket.dao.UserDAO;
import com.example.task_2.hibernate_ticket.dao.implementations.TicketDaoImpl;
import com.example.task_2.hibernate_ticket.dao.implementations.UserDaoImpl;
import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;
import com.example.task_2.hibernate_ticket.entity.User;
import com.example.task_2.hibernate_ticket.service.TicketService;
import com.example.task_2.hibernate_ticket.service.UserService;
import com.example.task_2.hibernate_ticket.service.implementations.TicketServiceImpl;
import com.example.task_2.hibernate_ticket.service.implementations.UserServiceImpl;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HibernateTicketStarter {

    public static void main(String[] args) {
        FlywayConfig.applyDbMigrations();

        SessionFactory sessionFactory = SessionFactoryConfig.getSessionFactory();

        UserDAO userDAO = new UserDaoImpl(sessionFactory);
        TicketDAO ticketDAO = new TicketDaoImpl(sessionFactory);

        UserService userService = new UserServiceImpl(userDAO);
        TicketService ticketService = new TicketServiceImpl(ticketDAO);

        userService.saveUser(new User(null, "First User", Date.valueOf(LocalDate.now())));
        System.out.println(userService.getUserById(1L));

        generateListOfTickets(1L)
                .forEach(ticketService::saveTicket);

        System.out.println(ticketService.getTicketById(2L));
        ticketService.updateTicketType(2L, TicketType.YEAR);

        userService.updateUserNameAndAllTicketTypes(1L, "New name", TicketType.DAY);
        userService.getUserTickets(1L)
                .forEach(System.out::println);

        userService.deleteTicketById(1L);
    }

    private static List<Ticket> generateListOfTickets(Long userId) {
        List<Ticket> ticketList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            ticketList.add(
                    new Ticket(null, TicketType.values()[random.nextInt(4)],
                            Date.valueOf(LocalDate.now()), new User(userId))
            );
        }

        return ticketList;
    }
}
