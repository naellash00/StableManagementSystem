package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.ApiResponse.ApiException;
import com.example.stablemanagementsystem.Model.Subscription;
import com.example.stablemanagementsystem.Repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, CoachService coachService) {
        this.subscriptionRepository = subscriptionRepository;
    }

    // get subscription details
    public List<Subscription> getAllSubscribes() {
        return subscriptionRepository.findAll();
    }

    public void addSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    public void updateSubscription(Integer id, Subscription subscription) {
        Subscription oldSubscription = subscriptionRepository.findSubscriptionById(id);
        if (oldSubscription == null) {
            throw new ApiException("Subscription Dose Not Exit");
        }
        oldSubscription.setType(subscription.getType());
        oldSubscription.setPrice(subscription.getPrice());
        oldSubscription.setNumberofclasses(subscription.getNumberofclasses());
        subscriptionRepository.save(oldSubscription);
    }

    public void deleteSubscription(Integer id) {
        Subscription subscription = subscriptionRepository.findSubscriptionById(id);
        if (subscription == null) {
            throw new ApiException("Subscription Dose Not Exit");
        }
        subscriptionRepository.delete(subscription);
    }

    public Integer getPriceByType(String type) {
        Integer price = 0;
        if (type.equalsIgnoreCase("riding")) {
            price = 1000;
        } else if (type.equalsIgnoreCase("pegging")) {
            price = 1500;
        } else if (type.equalsIgnoreCase("jumping")) {
            price = 2000;
        }
        return price;
    }

    public Integer getNumberOfClassesByType(String type) {
        Integer numberOfClasses = 0;
        if (type.equalsIgnoreCase("riding")) {
            numberOfClasses = 12;
        } else if (type.equalsIgnoreCase("pegging")) {
            numberOfClasses = 10;
        } else if (type.equalsIgnoreCase("jumping")) {
            numberOfClasses = 6;
        }
        return numberOfClasses;
    }


    public Subscription setSubscriptionDetailsBasedOnType(String type) {
        Subscription subscription = new Subscription();
        if (type.equalsIgnoreCase("riding")) {
            subscription.setType(type);
            subscription.setPrice(1000);
            subscription.setNumberofclasses(12);
        } else if (type.equalsIgnoreCase("pegging")) {
            // coach approve
            subscription.setType(type);
            subscription.setPrice(1500);
            subscription.setNumberofclasses(10);
        } else if (type.equalsIgnoreCase("jumping")) {
            //coach approve
            subscription.setType(type);
            subscription.setPrice(2000);
            subscription.setNumberofclasses(6);
        } else {
            throw new ApiException("Incorrect Subscription Type");
        }
        return subscription;
    }
    public Subscription getSubscriptionByTraineeId(Integer traineeID){
        return subscriptionRepository.getSubscriptionByTraineeId(traineeID);
    }


}
