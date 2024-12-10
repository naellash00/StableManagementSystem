package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.ApiResponse.ApiException;
import com.example.stablemanagementsystem.Model.Coach;
import com.example.stablemanagementsystem.Model.GroomingRequest;
import com.example.stablemanagementsystem.Repository.CoachRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CoachService {
    private final CoachRepository coachRepository;
    private final GroomingRequestService groomingRequestService;

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public void addCoach(Coach coach) {
        coachRepository.save(coach);
    }

    public void updateCoach(Integer id, Coach coach) {
        Coach oldCoach = coachRepository.findCoachById(id);
        if (oldCoach == null) {
            throw new ApiException("Coach ID Not Found");
        }
        oldCoach.setAge(coach.getAge());
        oldCoach.setEmail(coach.getEmail());
        oldCoach.setFullName(coach.getFullName());
        oldCoach.setPhone(coach.getPhone());
        oldCoach.setSalary(coach.getSalary());
        coachRepository.save(oldCoach);
    }

    public void deleteCoach(Integer id) {
        Coach coach = coachRepository.findCoachById(id);
        if (coach == null) {
            throw new ApiException("Coach ID Not Found");
        }
        coachRepository.delete(coach);
    }

    public void acceptGroomingRequest(Integer horseOwnerId, String serviceName) {
        for (GroomingRequest request : groomingRequestService.getGroomingRequests()) {
            if (request.getServiceName().equals(serviceName) &&
                    request.getHorseOwnerId().equals(horseOwnerId) &&
                    !request.getIsAccepted()) {
                request.setIsAccepted(true);
                return;
            }
        }
        throw new ApiException("Grooming Request not found or already accepted");
    }
}

