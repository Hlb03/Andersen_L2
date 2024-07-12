package com.example.task_2.jdbc_ticket;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {

    private static PGSimpleDataSource postgresDataSource;

    private DataSourceConfig() {}

    public static synchronized DataSource getDataSource() {
        if (postgresDataSource != null)
            return postgresDataSource;

        postgresDataSource = new PGSimpleDataSource();
        postgresDataSource.setUrl("jdbc:postgresql://localhost:5432/my_ticket_service_db");
        postgresDataSource.setUser("postgres");
        postgresDataSource.setPassword("postgres");
        return postgresDataSource;
    }
}
