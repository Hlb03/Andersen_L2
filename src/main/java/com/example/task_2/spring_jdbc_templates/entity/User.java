package com.example.task_2.spring_jdbc_templates.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Column(name = "creation_date")
    private Date creationDate;

    @OneToMany
    private List<Address> addresses;
}
