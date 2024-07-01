package com.example.task_2.jdbc_ticket.dao.implementations;

import com.example.task_2.jdbc_ticket.dao.UserDAO;
import com.example.task_2.jdbc_ticket.entity.User;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class UserDaoImpl implements UserDAO {

    private final DataSource dataSource;

    private final String DELETE_USER_BY_ID_QUERY = "DELETE FROM \"user\" WHERE id = ?";
    private final String GET_USER_BY_ID_QUERY = "SELECT * FROM \"user\" WHERE id = ?";
    private final String INSERT_INTO_USER_QUERY = "INSERT INTO \"user\" (name, creation_date) VALUES (?, ?)";

    @Override
    public void deleteUser(Long userId) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(DELETE_USER_BY_ID_QUERY)
        ) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(Long userId) {
        User user = new User();

        try (
                Connection con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(GET_USER_BY_ID_QUERY);
        ) {
            statement.setLong(1, userId);
            System.out.println("Comes over here");
            try (
                    ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    user.setId(userId);
                    user.setName(rs.getString("name"));
                    user.setCreationDate(rs.getDate("creation_date"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        try (
                Connection con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement(INSERT_INTO_USER_QUERY)
                ) {
            statement.setString(1, user.getName());
            statement.setDate(2, user.getCreationDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
