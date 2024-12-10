package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.ApiResponse.ApiException;
import com.example.stablemanagementsystem.Model.FreeRide;
import com.example.stablemanagementsystem.Model.RidingClass;
import com.example.stablemanagementsystem.Model.Visitor;
import com.example.stablemanagementsystem.Repository.FreeRideRepository;
import com.example.stablemanagementsystem.Repository.RidingClassRepository;
import com.example.stablemanagementsystem.Repository.VisitorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final FreeRideService freeRideService;
    private final FreeRideRepository freeRideRepository;
    private final RidingClassService ridingClassService;
    private final RidingClassRepository ridingClassRepository;
    private final SubscriptionService subscriptionService;

    public List<Visitor> getAllVisitors() {
        List<Visitor> visitors = visitorRepository.findAll();
        return visitors;
    }

    public void addVisitor(Visitor visitor) {
        visitorRepository.save(visitor);
    }

    public void updateVisitor(Integer id, Visitor visitor) {
        Visitor oldVisitor = visitorRepository.findVisitorById(id);
        if (oldVisitor == null) {
            throw new ApiException("Visitor Not Found");
        }
        oldVisitor.setUsername(visitor.getUsername());
        oldVisitor.setBalance(visitor.getBalance());
        visitorRepository.save(visitor);
    }

    public void deleteVisitor(Integer id) {
        Visitor visitor = visitorRepository.findVisitorById(id);
        if (visitor == null) {
            throw new ApiException("Visitor Not Found");
        }
        visitorRepository.delete(visitor);
    }

    // durations in minute
    public void requestFreeRide(Integer visitorID, Integer duration, String rideType) {
        // check visitor id
        Visitor visitor = visitorRepository.findVisitorById(visitorID);
        if (visitor == null) {
            throw new ApiException("Visitor ID Not Found");
        }
        // check if there is avaialble horse
        Integer assignedHorseID = ridingClassService.assignedHorse();
        if (assignedHorseID == null) {
            throw new ApiException("No Available Horse");
        }
        // if there is available horse
        // check their balance based on the price
        Integer price = freeRideService.getPriceByType(rideType);
        if (visitor.getBalance() >= price) {
            // deduct the price from visitor balance
            visitor.setBalance(visitor.getBalance() - price);
            FreeRide ride = new FreeRide();
            ride.setDuration(duration);
            ride.setRideType(rideType);
            ride.setVisitorID(visitor.getId());
            visitorRepository.save(visitor);
            freeRideRepository.save(ride);
        } else {
            throw new ApiException("Balance Not Enough");
        }
    }

    public void sendFreeRideGift(Integer senderVisitorID, String receiverVisitorUsername, Integer duration, String type) {
        Visitor senderVisitor = visitorRepository.findVisitorById(senderVisitorID);
        Visitor receiverVisitor = visitorRepository.findVisitorByUsername(receiverVisitorUsername);
        if (senderVisitor == null) {
            throw new ApiException("ID Not Found. You Have To Register In The System");
        }
        if (receiverVisitor == null) {
            throw new ApiException("Username Not Found. User Have To Register In The System");
        }
//        requestFreeRide(senderVisitorID, duration, type);
        Integer assignedHorseID = ridingClassService.assignedHorse();
        if (assignedHorseID == null) {
            throw new ApiException("No Available Horse");
        }

        Integer price = freeRideService.getPriceByType(type);
        if (senderVisitor.getBalance() >= price) {
            // deduct the price from visitor balance
            senderVisitor.setBalance(senderVisitor.getBalance() - price);
            FreeRide ride = new FreeRide();
            ride.setDuration(duration);
            ride.setRideType(type);
            ride.setVisitorID(receiverVisitor.getId());
            visitorRepository.save(senderVisitor);
            visitorRepository.save(receiverVisitor);
            freeRideRepository.save(ride);
        } else {
            throw new ApiException("Balance Not Enough");
        }
    }
}
