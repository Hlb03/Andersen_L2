package com.example.task_2.bus_ticket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class BusTicketStarter {

    private final static String BUS_TICKET_FILE_LOCATION = "Andersen_L2/src/main/resources/bus_tickets.txt";

    public static void main(String[] args) throws IOException {
        BusTicketValidator ticketValidator = new BusTicketValidator();
        List<BusTicket> ticketsFromFile = getTicketsFromFile();

        ticketValidator.validateTickets(ticketsFromFile);
    }

    private static List<BusTicket> getTicketsFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        return mapper.readValue(Paths.get(BUS_TICKET_FILE_LOCATION).toFile(), new TypeReference<>(){});
    }
}