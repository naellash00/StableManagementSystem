package com.example.stablemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class ShelterHorse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name Cannot Be Empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "breed Cannot be Empty")
    @Pattern(regexp="^(arabic|english|hybrid)$", message = "Breed Should Be One Of The Options: 'arabic'-'english'-'hybrid'")
    @Column(columnDefinition = "varchar(30) not null")
    private String breed;

    @NotNull(message = "Age Cannot Be Empty")
    @Column(columnDefinition = "int not null")
    private Integer age;


    @Column(columnDefinition = "int not null unique")
    private Integer roomNumber;

    @Column(columnDefinition = "int not null")
    private Integer ownerID;
}
