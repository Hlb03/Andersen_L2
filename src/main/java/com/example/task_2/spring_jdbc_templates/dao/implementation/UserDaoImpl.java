package com.example.task_2.spring_jdbc_templates.dao.implementation;

import com.example.task_2.spring_jdbc_templates.dao.UserDao;
import com.example.task_2.spring_jdbc_templates.entity.User;
import com.example.task_2.spring_jdbc_templates.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    private final String GET_USER_BY_ID_QUERY = "SELECT * FROM \"user\" WHERE id = ?";
    private final String UPDATE_USER_NAME_QUERY = "UPDATE \"user\" SET name = ? WHERE id = ?";

    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, userMapper, id);
    }

    @Override
    public void updateUserName(Long userId, String newName) {
        jdbcTemplate.update(UPDATE_USER_NAME_QUERY, newName, userId);
    }
}
