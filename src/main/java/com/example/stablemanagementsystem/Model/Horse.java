package com.example.stablemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Check(constraints = "status='available' or status = 'assigned'")
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name Cannot Be Empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "breed Cannot be Empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String breed;

    @NotNull(message = "Age Cannot Be Empty")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotNull(message = "Room Number Cannot Be Empty")
    @Column(columnDefinition = "int not null unique")
    private Integer roomnumber;

    @NotEmpty(message = "Status Cannot Be Empty")
    @Pattern(regexp = "^(available|assigned)$")
    @Column(columnDefinition = "varchar(10) not null")
    private String status;

    // SETTERS AND GETTERS


    public @NotEmpty(message = "Status Cannot Be Empty") @Pattern(regexp = "^(available|assigned)$") String getStatus() {
        return status;
    }

    public void setStatus(@NotEmpty(message = "Status Cannot Be Empty") @Pattern(regexp = "^(available|assigned)$") String status) {
        this.status = status;
    }

    public @NotEmpty(message = "Name Cannot Be Empty") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name Cannot Be Empty") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "breed Cannot be Empty") String getBreed() {
        return breed;
    }

    public void setBreed(@NotEmpty(message = "breed Cannot be Empty") String breed) {
        this.breed = breed;
    }

    public @NotNull(message = "Age Cannot Be Empty") Integer getAge() {
        return age;
    }

    public void setAge(@NotNull(message = "Age Cannot Be Empty") Integer age) {
        this.age = age;
    }

    public @NotNull(message = "Room Number Cannot Be Empty") Integer getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(@NotNull(message = "Room Number Cannot Be Empty") Integer roomnumber) {
        this.roomnumber = roomnumber;
    }
}



