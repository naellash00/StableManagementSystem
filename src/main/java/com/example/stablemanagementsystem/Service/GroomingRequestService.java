package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.Model.GroomingRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroomingRequestService {
    private List<GroomingRequest> groomingRequests = new ArrayList<>();

    public List<GroomingRequest> getGroomingRequests() {
        return groomingRequests;
    }

    public void addGroomingRequest(GroomingRequest request) {
        groomingRequests.add(request);
    }

    //
    public List<GroomingRequest> getAllAcceptedGroomingRequests(Integer horseOwnerId) {
        List<GroomingRequest> acceptedRequests = new ArrayList<>();

        for (GroomingRequest request : getGroomingRequests()) {
            if (request.getIsAccepted()) {
                request.setHorseOwnerId(horseOwnerId);
                acceptedRequests.add(request);
            }
        }

        return acceptedRequests;
    }

}
