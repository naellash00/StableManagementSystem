package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.Model.FreeRide;
import com.example.stablemanagementsystem.Repository.FreeRideRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FreeRideService {
    private final FreeRideRepository freeRideRepository;

    public List<FreeRide> getAllFreeRides() {
        List<FreeRide> rides = freeRideRepository.findAll();
        return rides;
    }

    public Integer getPriceByType(String type) {
        Integer price = 0;
        if (type.equalsIgnoreCase("with guidance")) {
            price = 4;
        } else if (type.equalsIgnoreCase("without guidance")) {
            price = 2;
        }
        return price;
    }
}
