package com.aritizate.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pqrs")
@Data
public class Pqrs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String message;
}
