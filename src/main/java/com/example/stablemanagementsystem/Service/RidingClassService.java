package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.ApiResponse.ApiException;
import com.example.stablemanagementsystem.Model.Coach;
import com.example.stablemanagementsystem.Model.Horse;
import com.example.stablemanagementsystem.Model.RidingClass;
import com.example.stablemanagementsystem.Model.Trainee;
import com.example.stablemanagementsystem.Repository.RidingClassRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RidingClassService {
    private final RidingClassRepository ridingClassRepository;
    private final HorseService horseService;
    private final CoachService coachService;

    public RidingClassService(RidingClassRepository ridingClassRepository, HorseService horseService, CoachService coachService) {
        this.ridingClassRepository = ridingClassRepository;
        this.horseService = horseService;
        this.coachService = coachService;
    }


    public List<RidingClass> getClassesInformation() {
        return ridingClassRepository.findAll();
    }
    public RidingClass getClassByDate(LocalDateTime date){
        RidingClass ridingClass = ridingClassRepository.findByDate(date);
        if(ridingClass==null){
            throw new ApiException("No Class In This Date");
        }
        return ridingClass;
    }


    // because of the qurey that deals with unique this makes only 2 trainees have same date
    public Boolean isAvailableDate(LocalDateTime date) {
        RidingClass ridingClass = ridingClassRepository.findByDate(date);
        return ridingClass == null || ridingClass.getNumberoftrainees() < 6;//
    }


    public Integer assignedCoach() {
        for (Coach coach : coachService.getAllCoaches()) {
            // check is this coach status is available
            if (coach.getStatus().equalsIgnoreCase("available")) {
                coach.setStatus("assigned");
                return coach.getId();
            }
        }
        return null; // no available coach
    }

    public Integer assignedHorse() {
        for (Horse horse : horseService.getAllHorses()) {
            if (horse.getStatus().equalsIgnoreCase("available")) {
                horse.setStatus("assigned");
                return horse.getId();
            }
        }
        return null; // no available horse
    }
}
