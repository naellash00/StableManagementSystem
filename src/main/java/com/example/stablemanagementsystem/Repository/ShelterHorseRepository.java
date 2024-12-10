package com.example.stablemanagementsystem.Repository;

import com.example.stablemanagementsystem.Model.ShelterHorse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterHorseRepository extends JpaRepository<ShelterHorse, Integer> {
    ShelterHorse findShelterHorseById(Integer id);
}
