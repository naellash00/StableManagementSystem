package com.example.stablemanagementsystem.Controller;

import com.example.stablemanagementsystem.Model.Horse;
import com.example.stablemanagementsystem.Service.HorseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/horse")
public class HorseController {
    private final HorseService horseService;
    public HorseController(HorseService horseService) {
        this.horseService = horseService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllHorses(){
        List<Horse> horses = horseService.getAllHorses();
        return ResponseEntity.status(200).body(horses);
    }

    @PostMapping("/add")
    public ResponseEntity addHorse(@RequestBody @Valid Horse horse){
        horseService.addHorse(horse);
        return ResponseEntity.status(200).body("Horse Added Successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateHorse(@PathVariable Integer id, @RequestBody @Valid Horse horse){
        horseService.updateHorse(id, horse);
        return ResponseEntity.status(200).body("Horse Updated Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHorse(@PathVariable Integer id){
        horseService.deleteHorse(id);
        return ResponseEntity.status(200).body("Horse Deleted Successfully");
    }
}
