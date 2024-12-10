package com.example.stablemanagementsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Stable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(30) not null unique")
    private String name;
    @Column(columnDefinition = "varchar(30) not null unique")
    private String address;
    @Column(columnDefinition = "int not null")
    private Integer numberOfRooms;

//    private Integer numberOfHorses=0;
//    private Integer numberOfCoaches=0;
//    private Integer numberOfTrainees=0;

}
