package com.example.stablemanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class RidingClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Class Date Cannot Be Empty")
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, unique = true)
    private LocalDateTime date;

    private String day;
    @PrePersist
    @PreUpdate
    public void setDay() {
        if (date != null) {
            this.day = date.getDayOfWeek().toString();
        }
    }

    private Integer numberoftrainees=0;
    private Integer numberofcoaches=0;
    private Integer numberOfHorses=0;

    @ElementCollection
    private List<Integer> traineeIds = new ArrayList<>();
}
