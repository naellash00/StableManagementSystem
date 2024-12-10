package com.example.stablemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Visitor Username Cannot Be Empty")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String username;

    @NotNull(message = "Balance Cannot Be Empty")
    @Column(columnDefinition = "int not null")
    private Integer balance;
}
