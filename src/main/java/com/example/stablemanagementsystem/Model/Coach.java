package com.example.stablemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Check(constraints = "(age >= 20) and (status = 'available' or status = 'assigned')")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name Cannot Be Empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String fullName;

    @NotNull(message = "Age Cannot Be Empty")
    @Min(value = 20, message = "Coach Age Cannot Be Less Than 20")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Phone Number Cannot Be Empty")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phone;


    @NotEmpty(message = "Email Cannot Be Empty")
    @Email(message = "Enter A Valid Email")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;


    @NotNull(message = "Salary Cannot Be Empty")
    @Column(columnDefinition = "int not null")
    private Integer Salary;

    @NotEmpty(message = "Coach Status Cannot Be Empty")
    @Pattern(regexp = "^(available|assigned)$")
    @Column(columnDefinition = "varchar(10) not null")
    private String status;

    // SETTERS AND GETTERS
}
