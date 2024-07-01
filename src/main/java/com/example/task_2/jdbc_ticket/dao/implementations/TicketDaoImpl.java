package com.example.task_2.jdbc_ticket.dao.implementations;

import com.example.task_2.jdbc_ticket.dao.TicketDAO;
import com.example.task_2.jdbc_ticket.entity.Ticket;
import com.example.task_2.jdbc_ticket.entity.TicketType;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TicketDaoImpl implements TicketDAO {

    private final DataSource dataSource;

    private final String GET_TICKET_BY_ID_QUERY = "SELECT ticket.id, ticket_type, ticket.creation_date, user_id " +
            "FROM \"ticket\" JOIN \"user\" ON ticket.user_id = \"user\".id WHERE ticket.id = ?";
    private final String GET_TICKETS_BY_USER_ID_QUERY = "SELECT ticket.id, ticket_type, ticket.creation_date, user_id " +
            "FROM \"ticket\" JOIN \"user\" ON ticket.user_id = \"user\".id WHERE user_id = ?";
    private final String INSERT_TICKET_QUERY = "INSERT INTO \"ticket\" (ticket_type, creation_date, user_id) VALUES (?::ticket_type, ?, ?)";
    private final String UPDATE_TICKET_TYPE_BY_ID_QUERY = "UPDATE \"ticket\" SET ticket_type = ?::ticket_type WHERE id = ?";
    private final String UPDATE_USER_NAME_BY_ID_QUERY = "UPDATE \"user\" SET name = ? WHERE id = ?";

    @Override
    public Ticket getTicketById(Long ticketId) {
        Ticket ticket = new Ticket();

        try (
                Connection con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(GET_TICKET_BY_ID_QUERY)
        ) {
            statement.setLong(1, ticketId);
            try (
                    ResultSet rs = statement.executeQuery()
            ) {
                while (rs.next()) {
                    ticket.setId(ticketId);
                    ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
                    ticket.setCreationDate(rs.getDate("creation_date"));
                    ticket.setUserId(rs.getLong("user_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ticket;
    }

    @Override
    public List<Ticket> getTicketsByUserId(Long userId) {
        List<Ticket> userTickets = new ArrayList<>();

        try (
                Connection con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(GET_TICKETS_BY_USER_ID_QUERY)
        ) {
            statement.setLong(1, userId);
            try (
                    ResultSet rs = statement.executeQuery()
            ) {
                while (rs.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(rs.getLong("id"));
                    ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
                    ticket.setCreationDate(rs.getDate("creation_date"));
                    ticket.setUserId(rs.getLong("user_id"));
                    userTickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userTickets;
    }

    @Override
    public void saveTicket(Ticket ticket) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(INSERT_TICKET_QUERY)
        ) {
            statement.setString(1, ticket.getTicketType().name());
            statement.setDate(2, ticket.getCreationDate());
            statement.setLong(3, ticket.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTicketType(Long ticketId, TicketType newType) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(UPDATE_TICKET_TYPE_BY_ID_QUERY)
        ) {
            statement.setString(1, newType.name());
            statement.setLong(2, ticketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTicketTypeAndUserName(Long ticketId, TicketType newType, Long userId, String newName) {
        Connection con = null;
        Savepoint beforeUpdateSavepoint = null;
        PreparedStatement ticketStatement = null;
        PreparedStatement userStatement = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            beforeUpdateSavepoint = con.setSavepoint();
            ticketStatement = con.prepareStatement(UPDATE_TICKET_TYPE_BY_ID_QUERY);
            ticketStatement.setString(1, newType.name());
            ticketStatement.setLong(2, ticketId);
            ticketStatement.executeUpdate();

            userStatement = con.prepareStatement(UPDATE_USER_NAME_BY_ID_QUERY);
            userStatement.setString(1, newName);
            userStatement.setLong(2, userId);
            userStatement.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback(beforeUpdateSavepoint);
                System.out.println("Savepoint worked. Update execution failed");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                con.close();
                ticketStatement.close();
                userStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
