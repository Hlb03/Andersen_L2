package com.example.task_2.hibernate_ticket;

import com.example.task_2.hibernate_ticket.entity.Ticket;
import com.example.task_2.hibernate_ticket.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryConfig {

    private static SessionFactory sessionFactory;

    private SessionFactoryConfig() {    }

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory != null)
            return sessionFactory;

        Configuration configuration = new Configuration();
        ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        //add mapping
        configuration.addAnnotatedClass(Ticket.class);
        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration
                .buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
