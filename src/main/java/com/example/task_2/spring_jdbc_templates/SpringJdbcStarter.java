package com.example.task_2.spring_jdbc_templates;

import com.example.task_2.bus_ticket.BusTicket;
import com.example.task_2.db_migration.FlywayConfig;
import com.example.task_2.spring_jdbc_templates.entity.Address;
import com.example.task_2.spring_jdbc_templates.entity.User;
import com.example.task_2.spring_jdbc_templates.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public class SpringJdbcStarter {

    public static void main(String[] args) throws IOException {
        FlywayConfig.applyDbMigrations();
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user = userService.getUserById(2L);
        Address address = new Address("Great Street 2b", user);

        userService.updateTicketNameAndSaveAddress(2L, "Another name for user", address);

        getDataFromFileWithSpring(context)
                .forEach(System.out::println);
    }

    public static List<BusTicket> getDataFromFileWithSpring(ApplicationContext context) throws IOException {
        Resource resource = context.getResource("classpath:/bus_tickets.txt");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        return mapper.readValue(resource.getFile(), new TypeReference<>(){});
    }
}
