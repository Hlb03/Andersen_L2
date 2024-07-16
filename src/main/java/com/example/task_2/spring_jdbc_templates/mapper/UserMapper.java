package com.example.task_2.spring_jdbc_templates.mapper;

import com.example.task_2.spring_jdbc_templates.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setCreationDate(rs.getDate("creation_date"));
        return user;
    }
}
