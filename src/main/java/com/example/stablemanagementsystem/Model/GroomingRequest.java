package com.example.stablemanagementsystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class GroomingRequest {
    @Pattern(regexp = "^(full service|bath service|hoof service)$", message = "enter available service: full service-bath service-hoof service")
    private String serviceName;
    private Boolean isAccepted = false;
    private Integer horseOwnerId; //  horse owner id

}
