package com.example.task_2.spring_boot.repository;

import com.example.task_2.spring_boot.entity.Ticket;
import com.example.task_2.spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> getTicketByUser(User user);
}
