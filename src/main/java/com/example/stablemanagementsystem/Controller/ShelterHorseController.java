package com.example.stablemanagementsystem.Controller;

import com.example.stablemanagementsystem.Model.ShelterHorse;
import com.example.stablemanagementsystem.Service.ShelterHorseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shelter/horse")
@AllArgsConstructor
public class ShelterHorseController {
    private final ShelterHorseService shelterHorseService;

   @GetMapping("/get/sheltered-horses")
    public ResponseEntity getAllShelteredHorses(){
        List<ShelterHorse> shelterHorses = shelterHorseService.getAllShelteredHorses();
        return ResponseEntity.status(200).body(shelterHorses);
    }

    @GetMapping("/get/shelter-horse/{id}")
    public ResponseEntity getShelteredHorseById(@PathVariable Integer id){
        ShelterHorse shelterHorse = shelterHorseService.getShelteredHorseById(id);
        return ResponseEntity.status(200).body(shelterHorse);
    }

}
