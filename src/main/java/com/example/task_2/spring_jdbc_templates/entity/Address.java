package com.example.task_2.spring_jdbc_templates.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Address {

    @Id
    @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "address_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
