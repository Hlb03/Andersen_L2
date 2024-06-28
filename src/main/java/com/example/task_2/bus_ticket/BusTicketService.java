package com.example.task_2.bus_ticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BusTicketService {

    private List<BusTicket> storage = new ArrayList<>();

    public BusTicket getTicketById(String ticketId) {
        for (BusTicket ticket : storage)
            if (ticket.getId().toString().equals(ticketId))
                return ticket;

        return null;
    }

    public void deleteTicketById(String ticketId) {
        storage.removeIf(ticket -> ticket.getId().toString().equals(ticketId));
    }

    /**
     * Returns a list of bus tickets each of that has the required type and matches the price bounds.
     *
     * @param type the desired type of ticket
     * @param startPrice the lowest bound of ticket price
     * @param endPrice the highest bound of ticket price
     * @return list of BusTickets that suits specified params
     */
    public List<BusTicket> getByTypeAndPriceRange(String type, BigDecimal startPrice, BigDecimal endPrice) {
        return storage.stream()
                .filter(ticket -> ticket.getTicketType().equals(type))
                .filter(ticket -> ticket.getPrice().compareTo(startPrice) > 0 && ticket.getPrice().compareTo(endPrice) < 0)
                .toList();
    }
}
