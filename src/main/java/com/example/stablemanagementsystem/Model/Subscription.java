package com.example.stablemanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Check(constraints = "(type='riding' or type='jumping' or type='pegging') and (numberofclasses >= 6 and numberofclasses <= 12)")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Subscription Type Cannot Be Empty")
    @Pattern(regexp = "^(riding|jumping|pegging)$", message = "Subscription Type Must Be Either: 'riding'-'jumping'-'pegging'")
    @Column(columnDefinition = "varchar(7) not null")
    private String type;

    @NotNull(message = "Price Cannot Be Empty")
    @Positive(message = "Price Must Be Positive")
    @Column(columnDefinition = "int not null")
    private Integer price;

    @NotNull(message = "Number Of Classes Cannot Be Empty")
    @Max(value = 12, message = "Number Of Classes Cannot Be More Than 12")
    @Min(value = 6, message = "Number Of Classes Cannot Be Less Than 6")
    @Column(columnDefinition = "int not null")
    private Integer numberofclasses;

    @Column(columnDefinition = "int unique")
    private Integer traineeid;



    // SETTERS AND GETTERS


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTraineeid() {
        return traineeid;
    }

    public void setTraineeid(Integer traineeid) {
        this.traineeid = traineeid;
    }

    public @NotEmpty(message = "Subscription Type Cannot Be Empty") @Pattern(regexp = "^(riding|jumping|pegging)$", message = "Subscription Type Must Be Either: 'riding'-'jumping'-'pegging'") String getType() {
        return type;
    }

    public void setType(@NotEmpty(message = "Subscription Type Cannot Be Empty") @Pattern(regexp = "^(riding|jumping|pegging)$", message = "Subscription Type Must Be Either: 'riding'-'jumping'-'pegging'") String type) {
        this.type = type;
    }

    public @NotNull(message = "Price Cannot Be Empty") @Positive(message = "Price Must Be Positive") Integer getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Price Cannot Be Empty") @Positive(message = "Price Must Be Positive") Integer price) {
        this.price = price;
    }

    public @NotNull(message = "Number Of Classes Cannot Be Empty") @Max(value = 12, message = "Number Of Classes Cannot Be More Than 12") @Min(value = 6, message = "Number Of Classes Cannot Be Less Than 6") Integer getNumberofclasses() {
        return numberofclasses;
    }

    public void setNumberofclasses(@NotNull(message = "Number Of Classes Cannot Be Empty") @Max(value = 12, message = "Number Of Classes Cannot Be More Than 12") @Min(value = 6, message = "Number Of Classes Cannot Be Less Than 6") Integer numberofclasses) {
        this.numberofclasses = numberofclasses;
    }
}
