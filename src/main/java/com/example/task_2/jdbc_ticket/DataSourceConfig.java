package com.example.task_2.jdbc_ticket;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        PGSimpleDataSource postgresDataSource = new PGSimpleDataSource();
        postgresDataSource.setUrl("jdbc:postgresql://localhost:5432/my_ticket_service_db");
        postgresDataSource.setUser("postgres");
        postgresDataSource.setPassword("postgres");
        return postgresDataSource;
    }
}
