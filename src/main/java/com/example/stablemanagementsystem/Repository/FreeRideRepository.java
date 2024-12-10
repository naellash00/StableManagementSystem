package com.example.stablemanagementsystem.Repository;
import com.example.stablemanagementsystem.Model.FreeRide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeRideRepository extends JpaRepository<FreeRide, Integer> {
    FreeRide findFreeRideById(Integer id);
}
