package com.example.task_2;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TicketService {

    private final static Map<String, Ticket> ticketStorage = new HashMap<>();

    static {
        ticketStorage.put("1", new Ticket("Hall", (short) 551));
        ticketStorage.put("2", new Ticket("Concert hall", (short) 990));
        ticketStorage.put("3", new Ticket("Opera Hall", (short) 13));
        ticketStorage.put("4", new Ticket());
        ticketStorage.put("5", new Ticket());
        ticketStorage.put("6", new Ticket());
        ticketStorage.put("7", new Ticket("7", "Some Hall", (short) 885, false, 'A', 13.5f, new BigDecimal("350.50")));
        ticketStorage.put("8", new Ticket("7", "Another", (short) 10, true, 'A', 13.5f, new BigDecimal("350.50")));
        ticketStorage.put("9", new Ticket("7", "One more", (short) 192, true, 'C', 21.3f, new BigDecimal("350.50")));
        ticketStorage.put("10", new Ticket("7", "New one", (short) 301, false, 'B', 4.0f, new BigDecimal("350.50")));
    }

    public static void main(String[] args) {
        Ticket emptyTicket = new Ticket();
        System.out.println("Empty ticket: " + emptyTicket);

        Ticket limitedTicket = new Ticket("Great Hall", (short) 101);
        System.out.println("Limited ticket: " + limitedTicket);

        Ticket fullTicket = new Ticket("A1lP", "The smallest", (short) 299, false,
                'B', 10.5f, new BigDecimal("399.99"));
        System.out.println("Full ticket: " + fullTicket);

        System.out.println(ticketStorage.get("8"));
    }


}
