package com.example.stablemanagementsystem.Controller;

import com.example.stablemanagementsystem.ApiResponse.ApiResponse;
import com.example.stablemanagementsystem.Model.Coach;
import com.example.stablemanagementsystem.Service.CoachService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coach")
public class CoachController {
    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllCoaches() {
        List<Coach> coaches = coachService.getAllCoaches();
        return ResponseEntity.status(200).body(coaches);
    }

    @PostMapping("/add")
    public ResponseEntity addCoach(@RequestBody @Valid Coach coach) {
        coachService.addCoach(coach);
        return ResponseEntity.status(200).body(new ApiResponse("Coach Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCoach(@PathVariable Integer id, @RequestBody @Valid Coach coach) {
        coachService.updateCoach(id, coach);
        return ResponseEntity.status(200).body(new ApiResponse("Coach Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCoach(@PathVariable Integer id) {
        coachService.deleteCoach(id);
        return ResponseEntity.status(200).body(new ApiResponse("Coach Deleted Successfully"));
    }

    @PutMapping("/accept/request/{id}/{serviceName}")
    public ResponseEntity acceptGroomingRequest(@PathVariable Integer id,@PathVariable String serviceName) {
        coachService.acceptGroomingRequest(id, serviceName);
        return ResponseEntity.status(200).body(new ApiResponse("Request Accepted Successfully"));
    }
}






