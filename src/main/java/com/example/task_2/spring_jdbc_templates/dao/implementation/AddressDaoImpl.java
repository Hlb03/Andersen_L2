package com.example.task_2.spring_jdbc_templates.dao.implementation;

import com.example.task_2.spring_jdbc_templates.dao.AddressDao;
import com.example.task_2.spring_jdbc_templates.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDaoImpl implements AddressDao {

    private final JdbcTemplate jdbcTemplate;

    private final String INSERT_ADDRESS_QUERY = "INSERT INTO \"address\" (name, user_id) VALUES (?, ?)";

    @Override
    public void saveAddress(Address address) {
        jdbcTemplate.update(INSERT_ADDRESS_QUERY, address.getName(), address.getUser().getId());
    }
}
