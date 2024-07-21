package com.example.task_2.spring_boot.controller;

import com.example.task_2.spring_boot.ThisIsMyFirstConditionalBean;
import com.example.task_2.spring_boot.entity.Ticket;
import com.example.task_2.spring_boot.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final ThisIsMyFirstConditionalBean conditionalBean;

    @GetMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket getTicketById(@PathVariable Long ticketId) {
        conditionalBean.sayHelloFromConditionalBean();
        return ticketService.getTicketById(ticketId);
    }
}