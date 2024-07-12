package com.example.task_2.db_migration;

import org.flywaydb.core.Flyway;

public class FlywayConfig {

    public static void applyDbMigrations() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/my_ticket_service_db", "postgres", "postgres")
                .load();

        flyway.migrate();
    }
}
