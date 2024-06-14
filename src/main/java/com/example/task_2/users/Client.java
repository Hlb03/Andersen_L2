package com.example.task_2.users;

public class Client implements RolePrinter {
    @Override
    public void printRole() {
        System.out.println("Client role");
    }

    public void getTicket() {
        System.out.println("Client is getting ticket");
    }
}
