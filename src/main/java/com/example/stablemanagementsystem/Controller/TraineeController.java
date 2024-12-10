package com.example.stablemanagementsystem.Controller;

import com.example.stablemanagementsystem.ApiResponse.ApiResponse;
import com.example.stablemanagementsystem.Model.RidingClass;
import com.example.stablemanagementsystem.Model.Trainee;
import com.example.stablemanagementsystem.Service.TraineeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trainee")
public class TraineeController {
    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllTrainees() {
        List<Trainee> trainees = traineeService.getAllTrainees();
        return ResponseEntity.status(200).body(trainees);
    }

    @PostMapping("/add")
    public ResponseEntity addTrainee(@RequestBody @Valid Trainee trainee) {
        traineeService.addTrainee(trainee);
        return ResponseEntity.status(200).body(new ApiResponse("Trainee Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTrainee(@PathVariable Integer id, @RequestBody @Valid Trainee trainee) {
        traineeService.updateTrainee(id, trainee);
        return ResponseEntity.status(200).body(new ApiResponse("Trainee Updated Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTrainee(@PathVariable Integer id) {
        traineeService.deleteTrainee(id);
        return ResponseEntity.status(200).body(new ApiResponse("Trainee Deleted Successfully"));
    }

    @PostMapping("/subscribe/{traineeid}/{type}")
    public ResponseEntity subscribe(@PathVariable Integer traineeid, @PathVariable String type) {
        traineeService.subscribe(traineeid, type);
        return ResponseEntity.status(200).body(new ApiResponse("Subscribed Successfully"));
    }

    @PostMapping("/book/class/{traineeid}/{date}")
    public ResponseEntity bookClass(@PathVariable Integer traineeid, @PathVariable LocalDateTime date) {
        traineeService.bookClass(traineeid, date);
        return ResponseEntity.status(200).body(new ApiResponse("Class Is Booked Successfully"));
    }

    @PutMapping("/cancel/{traineeid}/{date}")
    public ResponseEntity cancelClass(@PathVariable Integer traineeid, @PathVariable LocalDateTime date) {
        traineeService.cancelClass(traineeid, date);
        return ResponseEntity.status(200).body(new ApiResponse("Class Is Canceled Successfully"));
    }

    @PutMapping("/renew/{traineeid}/{type}")
    public ResponseEntity renewSubscription(@PathVariable Integer traineeid, @PathVariable String type) {
        traineeService.renewSubscription(traineeid, type);
        return ResponseEntity.status(200).body(new ApiResponse("Your Subscription Is Renewed Successfully"));
    }

    @GetMapping("/get/trainee/history/{traineeid}")
    public ResponseEntity getTraineeClassHistory(@PathVariable Integer traineeid) {
        List<RidingClass> traineeClasses = traineeService.getTraineeClassHistory(traineeid);
        return ResponseEntity.status(200).body(traineeClasses);
    }

    @GetMapping("/find/available/classes/by/date/{date}")
    public ResponseEntity getAvailableClassesByDate(@PathVariable LocalDateTime date) {
        List<RidingClass> availableClasses = traineeService.getAvailableClasses(date);
        return ResponseEntity.status(200).body(availableClasses);
    }

    @PutMapping("/reschedule-class/{traineeid}/{oldDate}/{newDate}")
    public ResponseEntity rescheduleClass(@PathVariable Integer traineeid, @PathVariable LocalDateTime oldDate, @PathVariable LocalDateTime newDate) {
        traineeService.rescheduleClass(traineeid, oldDate, newDate);
        return ResponseEntity.status(200).body(new ApiResponse("Class Rescheduled Successfully"));
    }

    @GetMapping("/completed-classes/{date}")
    public ResponseEntity getCompletedClassesInDay(@PathVariable LocalDateTime date) {
        List<RidingClass> completedClasses = traineeService.getCompletedClassesInDay(date.toLocalDate());
        return ResponseEntity.status(200).body(completedClasses);
    }
}
