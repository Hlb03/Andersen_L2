package com.example.task_2.jdbc_ticket;

import com.example.task_2.db_migration.FlywayConfig;
import com.example.task_2.jdbc_ticket.dao.TicketDAO;
import com.example.task_2.jdbc_ticket.dao.UserDAO;
import com.example.task_2.jdbc_ticket.entity.Ticket;
import com.example.task_2.jdbc_ticket.entity.TicketType;
import com.example.task_2.jdbc_ticket.entity.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class JdbcTicketStarter {

    public static void main(String[] args) {
        FlywayConfig.applyDbMigrations();

        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        TicketDAO ticketDAO = context.getBean(TicketDAO.class);
        UserDAO userDao = context.getBean(UserDAO.class);

        User user = new User(null, "Random name", Date.valueOf(LocalDate.now()));
        userDao.saveUser(user);

        System.out.println(userDao.getUserById(1L));

        generateListOfTickets(1L)
                .forEach(ticketDAO::saveTicket);

        ticketDAO.getTicketsByUserId(1L)
                .forEach(System.out::println);
        ticketDAO.updateTicketType(3L, TicketType.DAY);
        System.out.println(ticketDAO.getTicketById(3L));

        // method that implements simultaneous update of two tables with savepoint usage
        ticketDAO.updateTicketTypeAndUserName(1L, TicketType.WEEK, 1L, "Updated value");

        userDao.deleteUser(1L);
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
