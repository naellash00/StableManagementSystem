package com.example.stablemanagementsystem.Controller;

import com.example.stablemanagementsystem.ApiResponse.ApiResponse;
import com.example.stablemanagementsystem.Model.Subscription;
import com.example.stablemanagementsystem.Service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscribes")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
    @GetMapping("/get")
    public ResponseEntity getAllSubscribes(){
        List<Subscription> subscribes = subscriptionService.getAllSubscribes();
        return ResponseEntity.status(200).body(subscribes);
    }
}
