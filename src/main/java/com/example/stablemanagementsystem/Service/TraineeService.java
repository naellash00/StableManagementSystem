package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.ApiResponse.ApiException;
import com.example.stablemanagementsystem.ApiResponse.ApiResponse;
import com.example.stablemanagementsystem.Model.RidingClass;
import com.example.stablemanagementsystem.Model.Subscription;
import com.example.stablemanagementsystem.Model.Trainee;
import com.example.stablemanagementsystem.Repository.RidingClassRepository;
import com.example.stablemanagementsystem.Repository.SubscriptionRepository;
import com.example.stablemanagementsystem.Repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;
    private final SubscriptionService subscriptionService;
    private final RidingClassService ridingClassService;
    private final RidingClassRepository ridingClassRepository;

    public TraineeService(TraineeRepository traineeRepository, SubscriptionRepository subscriptionRepository, SubscriptionService subscriptionService, RidingClassService ridingClassService, RidingClassRepository ridingClassRepository) {
        this.traineeRepository = traineeRepository;

        this.subscriptionService = subscriptionService;
        this.ridingClassService = ridingClassService;
        this.ridingClassRepository = ridingClassRepository;
    }

    public List<Trainee> getAllTrainees() {
        List<Trainee> trainees = traineeRepository.findAll();
        return trainees;
    }

    public void addTrainee(Trainee trainee) {
        traineeRepository.save(trainee);
    }

    public void updateTrainee(Integer id, Trainee trainee) {
        Trainee oldTrainee = traineeRepository.findTraineeById(id);
        if (oldTrainee == null) {
            throw new ApiException("Trainee ID Not Found");
        }
        oldTrainee.setFullName(trainee.getFullName());
        oldTrainee.setAge(trainee.getAge());
        oldTrainee.setPhone(trainee.getPhone());
        oldTrainee.setEmail(trainee.getEmail());
        traineeRepository.save(oldTrainee);
    }

    public void deleteTrainee(Integer id) {
        Trainee trainee = traineeRepository.findTraineeById(id);
        if (trainee == null) {
            throw new ApiException("Trainee ID Not Found");
        }
        traineeRepository.delete(trainee);
    }

    public void subscribe(Integer traineeID, String type) {
        // to subscribe 1.choose the type of subscription and then pay
        Boolean found = false; // flag to check trainee id
        for (Trainee trainee : getAllTrainees()) {
            // check the id of trainee who wants to subscribe is
            if (trainee.getId().equals(traineeID)) {
                found = true;
                // check balance
                if (trainee.getBalance() > subscriptionService.getPriceByType(type)) {
                    // pay
                    trainee.setBalance(trainee.getBalance() - subscriptionService.getPriceByType(type));
                    // create the new subscription
                    Subscription subscription = subscriptionService.setSubscriptionDetailsBasedOnType(type);
                    subscription.setTraineeid(trainee.getId());
                    // and add it
                    subscriptionService.addSubscription(subscription);
                    //trainee.setSubscriptionid(subscription.getId());
                    // System.out.println(isSubscribed(trainee.getId()));
                    // set the values of the number of classes
                    trainee.setNumberOfRemainingClasses(subscriptionService.getNumberOfClassesByType(type));
                    // and save it
                    traineeRepository.save(trainee);
                    isSubscribed(trainee.getId());
                } else {
                    throw new ApiException("Balance Not Enough To Subscribe");
                }
            }
        }
        if (!found) {
            throw new ApiException("Trainee ID Not Found");
        }
    }

    public Boolean isSubscribed(Integer traineeID) {
        // check id
        for (Trainee trainee : getAllTrainees()) {
            if (trainee.getId().equals(traineeID)) {
                return true;
            }
        }
        return false;
    }

//    public void bookClass(Integer traineeID, LocalDateTime date) {
//        Trainee trainee = traineeRepository.findTraineeById(traineeID);
//        if (trainee == null) {
//            throw new ApiException("Trainee ID Not Found");
//        }
//        if (!isSubscribed(traineeID)) {
//            throw new ApiException("Trainee IS Not Subscribed");
//        }
//        if (trainee.getNumberOfRemainingClasses() == 0) {
//            throw new ApiException("No Available Classes For This Trainee");
//        }
//        if (!ridingClassService.isAvailableDate(date)) {
//            throw new ApiException("This Date Is Unavailable");
//        }
//
//        // create new class and assign values
//        RidingClass ridingClass = ridingClassRepository.findByDate(date);
//        if (ridingClass == null) { // if its new date -> creat new object
//            ridingClass = new RidingClass();
//            ridingClass.setDate(date);
//            ridingClass.setNumberoftrainees(0); // Start with 0 trainees
//        }
//        if (ridingClass.getNumberoftrainees() >= 6) {
//            throw new ApiException("This Class Is Full");
//        }
//        // increment trainee count if not full
//        ridingClass.setNumberoftrainees(ridingClass.getNumberoftrainees() + 1);
//        if (!ridingClass.getTraineeIds().contains(traineeID)) {
//            ridingClass.getTraineeIds().add(traineeID);
//            ridingClass.setNumberoftrainees(ridingClass.getNumberoftrainees() + 1);
//        } else {
//            throw new ApiException("Trainee is already booked for this class");
//        }
//        //ridingClass.getTraineeIds().add(traineeID);
//        // to check if they trainee booked calss
//        // isBooked(traineeID, date);
//        // assign coach and horse
//        Integer assignedCoachID = ridingClassService.assignedCoach();
//        Integer assignedHorseID = ridingClassService.assignedHorse();
//        // ridingClass.setTraineeid(trainee.getId());
//        ridingClass.setDate(date);
//        if (assignedCoachID != null) {
//            //  ridingClass.setCoachid(assignedCoachID);
//            ridingClass.setNumberofcoaches(ridingClass.getNumberofcoaches() + 1);
//        } else {
//            throw new ApiException("No Available Coach");
//        }
//        if (assignedHorseID != null) {
//            //  ridingClass.setHorseid(assignedHorseID);
//            ridingClass.setNumberOfHorses(ridingClass.getNumberOfHorses() + 1);
//        } else {
//            throw new ApiException("No Available Horse");
//        }
//        ridingClassRepository.save(ridingClass);
//        trainee.setNumberOfRemainingClasses(trainee.getNumberOfRemainingClasses() - 1);
//        traineeRepository.save(trainee);
//    }

    public void bookClass(Integer traineeID, LocalDateTime date) {
        Trainee trainee = traineeRepository.findTraineeById(traineeID);
        if (trainee == null) {
            throw new ApiException("Trainee ID Not Found");
        }
        if (!isSubscribed(traineeID)) {
            throw new ApiException("Trainee IS Not Subscribed");
        }
        if (trainee.getNumberOfRemainingClasses() == 0) {
            throw new ApiException("No Available Classes For This Trainee");
        }
        if (!ridingClassService.isAvailableDate(date)) {
            throw new ApiException("This Date Is Unavailable");
        }


        RidingClass ridingClass = ridingClassRepository.findByDate(date);
        if (ridingClass == null) {
            ridingClass = new RidingClass();
            ridingClass.setDate(date);
            ridingClass.setNumberoftrainees(0); // Start with 0 trainees
        }
        if (ridingClass.getNumberoftrainees() >= 6) {
            throw new ApiException("This Class Is Full");
        }

        // chekc if trainee booked and add to id list
        if (!ridingClass.getTraineeIds().contains(traineeID)) {
            ridingClass.getTraineeIds().add(traineeID);
            ridingClass.setNumberoftrainees(ridingClass.getNumberoftrainees() + 1);
        } else {
            throw new ApiException("Trainee is already booked for this class");
        }

        // automatic assign coach and horse
        Integer assignedCoachID = ridingClassService.assignedCoach();
        Integer assignedHorseID = ridingClassService.assignedHorse();
        if (assignedCoachID != null) {
            ridingClass.setNumberofcoaches(ridingClass.getNumberofcoaches() + 1);
        } else {
            throw new ApiException("No Available Coach");
        }
        if (assignedHorseID != null) {
            ridingClass.setNumberOfHorses(ridingClass.getNumberOfHorses() + 1);
        } else {
            throw new ApiException("No Available Horse");
        }

        // Save updated class and trainee
        ridingClassRepository.save(ridingClass);
        trainee.setNumberOfRemainingClasses(trainee.getNumberOfRemainingClasses() - 1);
        traineeRepository.save(trainee);
    }


    public Boolean isBooked(Integer traineeID, LocalDateTime date) {
        RidingClass ridingClass = ridingClassRepository.findByDate(date);
        if (ridingClass == null) {
            throw new ApiException("No Class In This Date"); // no class exists for this date
        }
        // If the class exists
        return ridingClass.getNumberoftrainees() > 0; // manually entering the trainee
    }

    public void cancelClass(Integer traineeID, LocalDateTime date) {
        // to cancel class 1. check valid trainee id 2. check they already booked the class 3. check the time is before the class in 2 hourse
        Trainee trainee = traineeRepository.findTraineeById(traineeID);
        if (trainee == null) {
            throw new ApiException("Trainee ID Not Found");
        }
        RidingClass ridingClass = ridingClassRepository.findByDate(date);
        if (ridingClass == null) {
            throw new ApiException("No Class Found In This Date");
        }
        // reduce numbers in class
        ridingClass.setNumberoftrainees(ridingClass.getNumberoftrainees() - 1);
        ridingClass.setNumberofcoaches(ridingClass.getNumberofcoaches() - 1);
        ridingClass.setNumberOfHorses(ridingClass.getNumberOfHorses() - 1);
        ridingClassRepository.save(ridingClass);
    }

    public void renewSubscription(Integer traineeID, String type) {
        Trainee trainee = traineeRepository.findTraineeById(traineeID);
        //check trainee id
        if (trainee == null) {
            throw new ApiException("Trainee ID Not Found");
        }
        //check if trainee subscription is ended
        if (trainee.getNumberOfRemainingClasses() > 0) {
            throw new ApiException("You Cant Renew. Your Subscription Didnt End.");
        }
        // and is their subscription really ended they can renew it
        if (trainee.getNumberOfRemainingClasses() == 0) {
            // check their balance
            Integer price = subscriptionService.getPriceByType(type);
            if (trainee.getBalance() >= price) {
                Subscription existingSubscription = subscriptionService.getSubscriptionByTraineeId(traineeID);
                if (existingSubscription != null) {
                    subscriptionService.deleteSubscription(existingSubscription.getId());
                }
                // if their balance is enough, then set the new subscription data
                // update their balance
                trainee.setBalance(trainee.getBalance() - price);
                trainee.setNumberOfRemainingClasses(subscriptionService.getNumberOfClassesByType(type));
                Subscription newSubscription = subscriptionService.setSubscriptionDetailsBasedOnType(type);
                newSubscription.setTraineeid(trainee.getId());
                traineeRepository.save(trainee);
                subscriptionService.addSubscription(newSubscription);

            } else {
                throw new ApiException("Your Balance Not Enough");
            }
        }

    }

    public List<RidingClass> getTraineeClassHistory(Integer traineeID) {
        // all the classes
        List<RidingClass> allClasses = ridingClassRepository.findAll();
        // the trainee classes intially
        List<RidingClass> traineeClasses = new ArrayList<>();

        for (RidingClass ridingClass : allClasses) {
            if (ridingClass.getTraineeIds().contains(traineeID)) {
                traineeClasses.add(ridingClass);
            }
        }

        return traineeClasses;
    }

    public List<RidingClass> getAvailableClasses(LocalDateTime date) {
        List<RidingClass> availableClasses = new ArrayList<>();
        //find claases with the date
        List<RidingClass> allClasses = ridingClassRepository.findAll();

        for (RidingClass ridingClass : allClasses) {
            if (ridingClass.getDate().equals(date)
                    && ridingClass.getNumberoftrainees() < 6) { //  if class is not full
                availableClasses.add(ridingClass);
            }
        }

        return availableClasses;
    }

    //**
    public void rescheduleClass(Integer traineeID, LocalDateTime oldDate, LocalDateTime newDate) {
        Trainee trainee = traineeRepository.findTraineeById(traineeID);
        if (trainee == null) {
            throw new ApiException("Trainee ID Not Found");
        }

        RidingClass oldRidingClass = ridingClassRepository.findByDate(oldDate);
        if (oldRidingClass == null || !oldRidingClass.getTraineeIds().contains(traineeID)) {
            throw new ApiException("No Class Found On The Given Old Date");
        }

        RidingClass newRidingClass = ridingClassRepository.findByDate(newDate);
        if (newRidingClass != null && newRidingClass.getNumberoftrainees() >= 6) {
            throw new ApiException("New Date Class is Full");
        }

        // deltw the trainee from the old class
        oldRidingClass.getTraineeIds().remove(traineeID);
        oldRidingClass.setNumberoftrainees(oldRidingClass.getNumberoftrainees() - 1);
        ridingClassRepository.save(oldRidingClass);

        // add  trainee to  new  class
        if (newRidingClass == null) {
            newRidingClass = new RidingClass();
            newRidingClass.setDate(newDate);
            newRidingClass.setNumberoftrainees(0);
        }
        newRidingClass.getTraineeIds().add(traineeID);
        newRidingClass.setNumberoftrainees(newRidingClass.getNumberoftrainees() + 1);
        ridingClassRepository.save(newRidingClass);
    }

    public List<RidingClass> getCompletedClassesInDay(LocalDate date) {
        List<RidingClass> allClasses = ridingClassRepository.findAll();
        List<RidingClass> completedClasses = new ArrayList<>();

        for (RidingClass ridingClass : allClasses) {
            if (ridingClass.getDate().toLocalDate().equals(date)) {
                completedClasses.add(ridingClass);
            }
        }

        return completedClasses;
    }

}
