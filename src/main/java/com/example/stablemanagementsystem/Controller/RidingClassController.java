package com.example.stablemanagementsystem.Controller;

import com.example.stablemanagementsystem.Model.RidingClass;
import com.example.stablemanagementsystem.Service.RidingClassService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vi/class")
public class RidingClassController {
    private final RidingClassService ridingClassService;

    @GetMapping("/get/classes")
    public ResponseEntity getClassesInformation() {
        List<RidingClass> classes = ridingClassService.getClassesInformation();
        return ResponseEntity.status(200).body(classes);
    }
    @GetMapping("/get-class/by-date/{date}")
    public ResponseEntity getClassByDate(@PathVariable LocalDateTime date){
        RidingClass ridingClass = ridingClassService.getClassByDate(date);
        return ResponseEntity.status(200).body(ridingClass);
    }
}
