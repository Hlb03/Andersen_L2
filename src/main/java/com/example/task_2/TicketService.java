package com.example.task_2;


import com.example.task_2.custom_storages.CustomList;
import com.example.task_2.custom_storages.CustomSet;
import com.example.task_2.custom_storages.implementation.CustomArrayList;
import com.example.task_2.custom_storages.implementation.CustomHashSet;
import com.example.task_2.users.Admin;
import com.example.task_2.users.Client;
import com.example.task_2.users.RolePrinter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
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

        System.out.println(getTicketById("8")); //example of calling a method to get Ticket by ID

        //Homework_4
        System.out.println(fullTicket.getId()); //example of getting method from parent class Identifier

        fullTicket.print(); // calling Ticket-specific implementation of .print() method

        fullTicket.shared("+0178948398"); //compile-time polymorphism
        fullTicket.shared("+83782919289", "some@gmail.com"); //another one

        RolePrinter client = new Client();
        client.printRole(); //example of late binding (runtime polymorphism)

        RolePrinter admin = new Admin();
        admin.printRole(); // another example of runtime polymorphism

        System.out.println(fullTicket.equals(limitedTicket)); //equals and hashCode methods were overrided for the previous homework as they are must have parts of HashMap usage
        System.out.println(fullTicket.hashCode());

        CustomList<String> list = new CustomArrayList<>();
        list.put("Another one");
        list.put("One more");
        list.put("Another");
        list.put("Other");
        list.put("Over here");
        System.out.println(list);
        list.deleteByIndex(4);
        System.out.println(list);
        list.put("Random value");
        System.out.println(list);

        CustomSet<String> set = new CustomHashSet<>(5);
        set.put("One");
        set.put("Two");
        set.put("Three");
        set.put("Four");
        System.out.println("Result of inserting duplicate value is: " + set.put("One"));
        set.delete("Ten");
        set.delete("Three");
        System.out.println(set.contains("Three"));
        System.out.println(set.contains("One"));

        Iterator<String> setIterator = set.iterator();
        while (setIterator.hasNext())
            System.out.println("Element inside set: " + setIterator.next());
    }

    public static Ticket getTicketById(String ticketId) {
        return ticketStorage.get(ticketId);
    }
}
