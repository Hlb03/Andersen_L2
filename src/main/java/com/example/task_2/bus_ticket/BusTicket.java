package com.example.task_2.bus_ticket;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BusTicket {
    String ticketClass;
    String ticketType;
    LocalDate startDate;
    BigDecimal price;
}