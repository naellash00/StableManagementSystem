package com.example.stablemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Check(constraints = "duration >= 5 and duration <= 20")
public class FreeRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(value = 5, message = "Duration Cannot Be Less Than 5")
    @Max(value = 20, message = "Duration Cannot Be More Than 20")
    @Column(columnDefinition = "int not null")
    private Integer duration; // in minutes between 5min - 20min

    @Pattern(regexp = "^(with guidance|without guidance)$")
    @Column(columnDefinition = "varchar(17) not null")
    private String rideType;

    private Integer visitorID;
}
