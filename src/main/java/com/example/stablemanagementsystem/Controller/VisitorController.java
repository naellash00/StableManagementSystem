package com.example.stablemanagementsystem.Controller;

import com.example.stablemanagementsystem.ApiResponse.ApiResponse;
import com.example.stablemanagementsystem.Model.FreeRide;
import com.example.stablemanagementsystem.Model.Trainee;
import com.example.stablemanagementsystem.Model.Visitor;
import com.example.stablemanagementsystem.Service.FreeRideService;
import com.example.stablemanagementsystem.Service.VisitorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visitor")
@AllArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;
    private final FreeRideService freeRideService;

    @GetMapping("/get")
    public ResponseEntity getAllVisitors() {
        List<Visitor> visitors = visitorService.getAllVisitors();
        return ResponseEntity.status(200).body(visitors);
    }

    @PostMapping("/add")
    public ResponseEntity addVisitor(@RequestBody @Valid Visitor visitor) {
        visitorService.addVisitor(visitor);
        return ResponseEntity.status(200).body(new ApiResponse("Visitor Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateVisitor(@PathVariable Integer id, @RequestBody @Valid Visitor visitor) {
        visitorService.updateVisitor(id, visitor);
        return ResponseEntity.status(200).body(new ApiResponse("Visitor Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVisitor(@PathVariable Integer id) {
        visitorService.deleteVisitor(id);
        return ResponseEntity.status(200).body(new ApiResponse("Visitor deleted Successfully"));
    }

    @PostMapping("/request/ride/{visitorid}/{duration}/{ridetype}")
    public ResponseEntity requestFreeRide(@PathVariable Integer visitorid, @PathVariable Integer duration, @PathVariable String ridetype){
        visitorService.requestFreeRide(visitorid, duration, ridetype);
        return ResponseEntity.status(200).body(new ApiResponse("Request For Ride Completed Successfully"));
    }

    @GetMapping("/get/free/ride/info")
    public ResponseEntity getAllFreeRides(){
        List<FreeRide> ride = freeRideService.getAllFreeRides();
        return ResponseEntity.status(200).body(ride);
    }


    @PutMapping("{senderid}/send/gift/to/{receiverusername}/{duration}/{type}")
    public ResponseEntity sendFreeRideGift(@PathVariable Integer senderid, @PathVariable String receiverusername, @PathVariable Integer duration, @PathVariable String type){
        visitorService.sendFreeRideGift(senderid, receiverusername, duration, type);
        return ResponseEntity.status(200).body(new ApiResponse("Free Ride Gift Sent Successfully"));
    }
}
