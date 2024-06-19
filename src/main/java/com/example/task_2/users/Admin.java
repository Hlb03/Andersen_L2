package com.example.task_2.users;

public class Admin implements RolePrinter {
    @Override
    public void printRole() {
        System.out.println("Admin role");
    }

    public void checkTicket() {
        System.out.println("Admin is checking ticket");
    }
}
