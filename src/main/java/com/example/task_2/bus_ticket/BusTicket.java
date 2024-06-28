package com.example.task_2.bus_ticket;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class BusTicket {

    public BusTicket(String ticketType, LocalDate startDate) {
        this.ticketType = ticketType;
        this.startDate = startDate;
    }

    private final UUID id = UUID.randomUUID();
    private String ticketClass;
    private String ticketType;
    private LocalDate startDate;
    private BigDecimal price;
}