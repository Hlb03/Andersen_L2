package com.example.task_2;


import java.math.BigDecimal;

public class TicketService {

    public static void main(String[] args) {
        Ticket emptyTicket = new Ticket();
        System.out.println("Empty ticket: " + emptyTicket);

        Ticket limitedTicket = new Ticket("Great Hall", (short) 101);
        System.out.println("Limited ticket: " + limitedTicket);

        Ticket fullTicket = new Ticket("A1lP", "Hall", (short) 299, false,
                "B", 10.5f, new BigDecimal("399.99"));
        System.out.println("Full ticket: " + fullTicket);
    }


}
