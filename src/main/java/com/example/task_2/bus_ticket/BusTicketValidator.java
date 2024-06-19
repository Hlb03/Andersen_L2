package com.example.task_2.bus_ticket;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BusTicketValidator {

    static {
        ticketTypes = Arrays.stream(TicketTypes.values())
                .map(Enum::name)
                .toList();
    }

    private final static List<String> ticketTypes;
    private int startDateErrorCounter = 0;
    private int ticketTypeErrorCounter = 0;
    private int priceErrorCounter = 0;

    public void validateTickets(List<BusTicket> tickets) {
        for (int i = 0; i < tickets.size(); i++) {
            validateTicketFields(tickets.get(i), i + 1);
        }

        getTicketsStatistics(tickets.size());
    }

    private void validateTicketFields(BusTicket ticket, int ticketSequenceNumber) {
        validateTicketDate(ticket, ticketSequenceNumber);
        validateTicketType(ticket, ticketSequenceNumber);
        validateTicketPrice(ticket, ticketSequenceNumber);
    }

    private void getTicketsStatistics(int ticketsAmount) {
        System.out.println("Total=" + ticketsAmount);
        System.out.println("Valid=" + (ticketsAmount - maxErrors()));
        printMostPopularViolation();
    }

    private void validateTicketDate(BusTicket ticket, int ticketSequenceNumber) {
        if (ticket.getStartDate() == null) {
            printToConsoleNullException(ticketSequenceNumber, "startDate");
            startDateErrorCounter++;
            return;
        }

        if (ticket.getStartDate().isAfter(LocalDate.now())) {
            System.out.println("Ticket" + ticketSequenceNumber + " startDate has a future date");
            startDateErrorCounter++;
        }
    }

    private void validateTicketType(BusTicket ticket, int ticketSequenceNumber) {
        if (ticket.getTicketType() == null) {
            printToConsoleNullException(ticketSequenceNumber, "ticketType");
            ticketTypeErrorCounter++;
            return;
        }

        if (!ticketTypes.contains(ticket.getTicketType())) {
            System.out.println("Ticket" + ticketSequenceNumber + " type " + "(" + ticket.getTicketType() + ") value is invalid");
            ticketTypeErrorCounter++;
        }
    }

    private void validateTicketPrice(BusTicket ticket, int ticketSequenceNumber) {
        if (ticket.getPrice() == null) {
            printToConsoleNullException(ticketSequenceNumber, "price");
            priceErrorCounter++;
            return;
        }

        if (ticket.getPrice().toString().equals("0")) {
            System.out.println("Ticket" + ticketSequenceNumber + " price should not be zero");
            priceErrorCounter++;
        }
        else if (ticket.getPrice().intValue() % 2 != 0) {
            System.out.println("Ticket" + ticketSequenceNumber + " price should have even value");
            priceErrorCounter++;
        }
    }

    private void printToConsoleNullException(int ticketSequenceNumber, String fieldNameToCheck) {
        System.out.println("Ticket" + ticketSequenceNumber + " has a null field '" + fieldNameToCheck + "'");
    }

    private void printMostPopularViolation() {
        System.out.print("Most popular violation=");
        if (startDateErrorCounter > ticketTypeErrorCounter && ticketTypeErrorCounter > priceErrorCounter)
            System.out.println("startDate");
        else if (ticketTypeErrorCounter > startDateErrorCounter && startDateErrorCounter > priceErrorCounter)
            System.out.println("ticketType");
        else System.out.println("price");
    }

    private int maxErrors() {
        if (startDateErrorCounter > ticketTypeErrorCounter && ticketTypeErrorCounter > priceErrorCounter)
            return priceErrorCounter;
        else if (ticketTypeErrorCounter > startDateErrorCounter && startDateErrorCounter > priceErrorCounter)
            return ticketTypeErrorCounter;
        return priceErrorCounter;
    }
}
