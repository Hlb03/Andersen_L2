package com.example.task_2.hibernate_service_test;

import com.example.task_2.hibernate_ticket.dao.UserDAO;
import com.example.task_2.hibernate_ticket.dao.implementations.UserDaoImpl;
import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.TicketType;
import com.example.task_2.hibernate_ticket.entity.User;
import com.example.task_2.hibernate_ticket.service.UserService;
import com.example.task_2.hibernate_ticket.service.implementations.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HibernateUserServiceTest {

    UserService userService;
    UserDAO userDAO = Mockito.mock(UserDaoImpl.class);

    @BeforeEach
    public void configUserService() {
        userService = new UserServiceImpl(userDAO);
    }

    @Test
    public void deleteTicketById() {
        doNothing().when(userDAO).deleteUserById(1L);
        userService.deleteTicketById(1L);

        verify(userDAO, times(1)).deleteUserById(1L);
    }

    @Test
    public void deleteTicketById_RuntimeException() {
        doThrow(new RuntimeException("Some message")).when(userDAO).deleteUserById(1L);

        Exception e = assertThrows(RuntimeException.class,
                () -> userService.deleteTicketById(1L));

        assertEquals("Some message", e.getMessage());
        verify(userDAO, times(1)).deleteUserById(1L);
    }

    @Test
    public void getUserById() {
        User user = new User(1L);

        when(userDAO.getUserById(1L)).thenReturn(user);
        User userFromTest = userService.getUserById(1L);

        assertEquals(1L, userFromTest.getId());
        verify(userDAO, times(1)).getUserById(1L);
    }

    @Test
    public void getUserById_RuntimeException() {
        when(userDAO.getUserById(1L)).thenThrow(new RuntimeException("Error message"));
        Exception e = assertThrows(RuntimeException.class,
                () -> userService.getUserById(1L));

        assertEquals("Error message", e.getMessage());
        verify(userDAO, times(1)).getUserById(1L);
    }

    @Test
    public void getUserTickets() {
        List<Ticket> tickets = List.of(
                new Ticket(1L),
                new Ticket(2L)
        );

        when(userDAO.getUserTickets(1L)).thenReturn(tickets);
        List<Ticket> ticketsFromTest = userService.getUserTickets(1L);

        assertEquals(2, ticketsFromTest.size());
        verify(userDAO, times(1)).getUserTickets(1L);
    }

    @Test
    public void getUserTickets_RuntimeException() {
        when(userDAO.getUserTickets(1L)).thenThrow(new RuntimeException("Failed to fetch tickets!"));
        Exception e = assertThrows(RuntimeException.class,
                () -> userService.getUserTickets(1L));

        assertEquals("Failed to fetch tickets!", e.getMessage());
        verify(userDAO, times(1)).getUserTickets(1L);
    }

    @Test
    public void saveUser() {
        User userToStore = new User(1L);

        doNothing().when(userDAO).saveUser(userToStore);
        userService.saveUser(userToStore);

        verify(userDAO, times(1)).saveUser(userToStore);
    }

    @Test
    public void saveUser_RuntimeException() {
        User u = new User(1L);

        doThrow(new RuntimeException("Failed to store user!")).when(userDAO).saveUser(u);
        Exception e = assertThrows(RuntimeException.class,
                () -> userService.saveUser(u));

        assertEquals("Failed to store user!", e.getMessage());
        verify(userDAO, times(1)).saveUser(u);
    }

    @Test
    public void updateUserNameAndCreateTicket() {
        Ticket ticketToStore = new Ticket(20L);

        doNothing().when(userDAO).updateUserNameAndSaveTicket(1L, "new Name", ticketToStore);
        userService.updateUserNameAndCreateTicket(1L, "new Name", ticketToStore);

        verify(userDAO, times(1)).updateUserNameAndSaveTicket(1L, "new Name", ticketToStore);
    }

    @Test
    public void updateUserNameAndCreateTicket_RuntimeException() {
        Ticket ticket = new Ticket(1L);

        doThrow(new RuntimeException("Failed to process operation!")).when(userDAO).updateUserNameAndSaveTicket(1L, "new Name", ticket);
        Exception e = assertThrows(RuntimeException.class,
                () -> userService.updateUserNameAndCreateTicket(1L, "new Name", ticket));

        assertEquals("Failed to process operation!", e.getMessage());
        verify(userDAO, times(1)).updateUserNameAndSaveTicket(1L, "new Name", ticket);
    }

    @Test
    public void updateUserNameAndAllTicketTypes() {
        doNothing().when(userDAO).updateUserNameAndAllTicketTypes(1L, "new Name", TicketType.YEAR);
        userService.updateUserNameAndAllTicketTypes(1L, "new Name", TicketType.YEAR);

        verify(userDAO, times(1)).updateUserNameAndAllTicketTypes(1L, "new Name", TicketType.YEAR);
    }

    @Test
    public void updateUserNameAndAllTicketTypes_RuntimeException() {
        doThrow(new RuntimeException("Failed to execute!")).when(userDAO).updateUserNameAndAllTicketTypes(1L, "new Name", TicketType.DAY);
        Exception e = assertThrows(RuntimeException.class,
                () -> userService.updateUserNameAndAllTicketTypes(1L, "new Name", TicketType.DAY));

        assertEquals("Failed to execute!", e.getMessage());
        verify(userDAO, times(1)).updateUserNameAndAllTicketTypes(1L, "new Name", TicketType.DAY);
    }
}
