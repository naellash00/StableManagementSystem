package com.example.stablemanagementsystem.Controller;

import com.example.stablemanagementsystem.ApiResponse.ApiResponse;
import com.example.stablemanagementsystem.Model.GroomingRequest;
import com.example.stablemanagementsystem.Model.HorseOwner;
import com.example.stablemanagementsystem.Model.ShelterHorse;
import com.example.stablemanagementsystem.Service.HorseOwnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/horse/owner")
@AllArgsConstructor
public class HorseOwnerController {
    private final HorseOwnerService horseOwnerService;

    @GetMapping("/get")
    public ResponseEntity getAllHorseOwners() {
        List<HorseOwner> horseOwners = horseOwnerService.getAllHorseOwners();
        return ResponseEntity.status(200).body(horseOwners);
    }

    @PostMapping("/add")
    public ResponseEntity addHorseOwner(@RequestBody @Valid HorseOwner horseOwner) {
        horseOwnerService.addHorseOwner(horseOwner);
        return ResponseEntity.status(200).body(new ApiResponse("Horse Owner Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateHorseOwner(@PathVariable Integer id, @RequestBody @Valid HorseOwner horseOwner) {
        horseOwnerService.updateHorseOwner(id, horseOwner);
        return ResponseEntity.status(200).body(new ApiResponse("Horse Owner Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHorseOwner(@PathVariable Integer id) {
        horseOwnerService.deleteHorseOwner(id);
        return ResponseEntity.status(200).body(new ApiResponse("Horse Owner Deleted Successfully"));
    }

    @PostMapping("/request/shelter/{horseownerid}/{duration}")
    public ResponseEntity requestHorseShelter(@PathVariable Integer horseownerid, @PathVariable Integer duration, @RequestBody @Valid ShelterHorse shelterhorse){
        horseOwnerService.requestHorseShelter(horseownerid, duration, shelterhorse);
        return ResponseEntity.status(200).body(new ApiResponse("Shelter Request Completed Successfully"));
    }

    @PutMapping("/request/room/change/{horseownerid}/{shelterhorseid}")
    public ResponseEntity requestRoomChange(@PathVariable Integer horseownerid, @PathVariable Integer shelterhorseid){
        horseOwnerService.requestRoomChange(horseownerid, shelterhorseid);
        return ResponseEntity.status(200).body(new ApiResponse("Room Request Is Changed Successfully"));
    }

    @PostMapping("/request/service/{horseOwnerId}")
    public ResponseEntity requestGroomingService(@PathVariable Integer horseOwnerId, @RequestBody @Valid GroomingRequest request){
        List<GroomingRequest> groomingRequests = horseOwnerService.requestGroomingService(horseOwnerId, request);
        return ResponseEntity.status(200).body(groomingRequests);
    }

    @GetMapping("/get/accepted/requests/{horseOwnerId}")
    public ResponseEntity getAllAcceptedGroomingRequestsByOwner(@PathVariable Integer horseOwnerId) {
        List<GroomingRequest> acceptedRequests = horseOwnerService.getAllAcceptedGroomingRequestsByOwner(horseOwnerId);
        return ResponseEntity.status(200).body(acceptedRequests);
    }
}
