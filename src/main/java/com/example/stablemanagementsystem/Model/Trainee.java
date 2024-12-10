package com.example.stablemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name Cannot Be Empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String fullName;

    @NotNull(message = "Age Cannot Be Empty")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Phone Number Cannot Be Empty")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phone;

    @NotEmpty(message = "Email Cannot Be Empty")
    @Email(message = "Enter A Valid Email")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    private Integer balance;
    private Integer numberOfRemainingClasses = 0;

}
