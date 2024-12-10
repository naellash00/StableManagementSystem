package com.example.stablemanagementsystem.Controller;

import com.example.stablemanagementsystem.ApiResponse.ApiResponse;
import com.example.stablemanagementsystem.Model.Stable;
import com.example.stablemanagementsystem.Service.StableService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stable")
@AllArgsConstructor
public class StableController {
    private final StableService stableService;

    @PostMapping("/add")
    public ResponseEntity addStable(@RequestBody @Valid Stable stable){
        stableService.addStable(stable);
        return ResponseEntity.status(200).body(new ApiResponse("Stable Added Successfully"));
    }

    @GetMapping("/get/stable/info/{id}")
    public ResponseEntity getStableInformation(@PathVariable Integer id){
        Stable stable = stableService.getStableInformation(id);
        return ResponseEntity.status(200).body(stable);
    }
}
