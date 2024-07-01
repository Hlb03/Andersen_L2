package com.example.task_2.jdbc_ticket;

import com.example.task_2.db_migration.FlywayConfig;
import com.example.task_2.jdbc_ticket.dao.TicketDAO;
import com.example.task_2.jdbc_ticket.dao.UserDAO;
import com.example.task_2.jdbc_ticket.dao.implementations.TicketDaoImpl;
import com.example.task_2.jdbc_ticket.dao.implementations.UserDaoImpl;
import com.example.task_2.jdbc_ticket.entity.Ticket;
import com.example.task_2.jdbc_ticket.entity.TicketType;
import com.example.task_2.jdbc_ticket.entity.User;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JdbcTicketStarter {

    public static void main(String[] args) {
        FlywayConfig.applyDbMigrations();

        DataSource source = DataSourceConfig.getDataSource();
        UserDAO userDAO = new UserDaoImpl(source);
        TicketDAO ticketDAO = new TicketDaoImpl(source);

        User user = new User(null, "Random name", Date.valueOf(LocalDate.now()));
        userDAO.saveUser(user);

        System.out.println(userDAO.getUserById(1L));

        generateListOfTickets(1L)
                .forEach(ticketDAO::saveTicket);

        ticketDAO.getTicketsByUserId(1L)
                .forEach(System.out::println);
        ticketDAO.updateTicketType(3L, TicketType.DAY);
        System.out.println(ticketDAO.getTicketById(3L));

        // method that implements simultaneous update of two tables with savepoint usage
        ticketDAO.updateTicketTypeAndUserName(1L, TicketType.WEEK, 1L, "Updated value");

        userDAO.deleteUser(1L);
        ticketDAO.getTicketsByUserId(1L)
                .forEach(System.out::println);
    }

    private static List<Ticket> generateListOfTickets(Long userId) {
        List<Ticket> ticketList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            ticketList.add(
                    new Ticket(null, Date.valueOf(LocalDate.now()),
                            TicketType.values()[random.nextInt(4)], userId)
            );
        }

        return ticketList;
    }
}
