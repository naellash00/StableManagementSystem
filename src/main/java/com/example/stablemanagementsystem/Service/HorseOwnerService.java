package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.ApiResponse.ApiException;
import com.example.stablemanagementsystem.Model.*;
import com.example.stablemanagementsystem.Repository.HorseOwnerRepository;
import com.example.stablemanagementsystem.Repository.ShelterHorseRepository;
import com.example.stablemanagementsystem.Repository.StableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HorseOwnerService {
    private final HorseOwnerRepository horseOwnerRepository;
    private final ShelterHorseService shelterHorseService;
    private final StableRepository stableRepository;
    private final ShelterHorseRepository shelterHorseRepository;
    private final GroomingRequestService groomingRequestService;

    public List<HorseOwner> getAllHorseOwners() {
        return horseOwnerRepository.findAll();
    }

    public void addHorseOwner(HorseOwner horseOwner) {
        horseOwnerRepository.save(horseOwner);
    }

    public void updateHorseOwner(Integer id, HorseOwner horseOwner) {
        HorseOwner oldHorseOwner = horseOwnerRepository.findHorseOwnerById(id);
        if (oldHorseOwner == null) {
            throw new ApiException("Horse Owner ID Not Found");
        }
        oldHorseOwner.setFullName(horseOwner.getFullName());
        oldHorseOwner.setPhoneNumber(horseOwner.getPhoneNumber());
        oldHorseOwner.setEmail(horseOwner.getEmail());
        oldHorseOwner.setBalance(horseOwner.getBalance());
        horseOwnerRepository.save(oldHorseOwner);
    }

    public void deleteHorseOwner(Integer id) {
        HorseOwner horseOwner = horseOwnerRepository.findHorseOwnerById(id);
        if (horseOwner == null) {
            throw new ApiException("Horse Owner ID Not Found");
        }
        horseOwnerRepository.delete(horseOwner);
    }

    public void requestHorseShelter(Integer horseOwnerID, Integer duration, ShelterHorse shelterHorse) {
        // first chekc valid owner id
        HorseOwner horseOwner = horseOwnerRepository.findHorseOwnerById(horseOwnerID);
        if (horseOwner == null) {
            throw new ApiException("Invalid Horse Owner ID");
        }
        Stable stable = stableRepository.findById(1).get();
        if (stable == null) {
            throw new ApiException("Stable Not Found");
        }
        if (stable.getNumberOfRooms() == 0) {
            throw new ApiException("No Available Rooms");
        }
        // check horse breed -? will be checkend in rquest body?
        // make calculation for the price based on duration
        Integer price = shelterHorseService.getShelterPriceBasedOnBreed(shelterHorse.getBreed()) * duration;
        // check owner balance
        if (horseOwner.getBalance() >= price) {
            horseOwner.setBalance(horseOwner.getBalance() - price);
            // set owner id
            shelterHorse.setOwnerID(horseOwnerID);
            // set automatic room number
            shelterHorse.setRoomNumber(shelterHorseService.assignAvailableRoom(stable));
            //decrement number of rooms
            stable.setNumberOfRooms(stable.getNumberOfRooms() - 1);
            //save changes
            stableRepository.save(stable);
            shelterHorseRepository.save(shelterHorse);
            horseOwnerRepository.save(horseOwner);
        } else {
            throw new ApiException("Balance Not Enough For Shelter Request And Duration");
        }
    }

    public void requestRoomChange(Integer horseOwnerID, Integer shelterHorseID) {
        // check owner and their horse id if exist
        HorseOwner horseOwner = horseOwnerRepository.findHorseOwnerById(horseOwnerID);
        ShelterHorse shelterHorse = shelterHorseRepository.findShelterHorseById(shelterHorseID);

        if (horseOwner == null) {
            throw new ApiException("Horse Owner ID Not Found");
        }
        if (shelterHorse == null) {
            throw new ApiException("Shelter Horse ID Not Found");
        }
        Stable stable = stableRepository.findById(1).get();
        if (stable == null) {
            throw new ApiException("Stable Not Found");
        }
        if (stable.getNumberOfRooms() == 0) {
            throw new ApiException("No Available Rooms");
        }
        shelterHorse.setRoomNumber(shelterHorseService.assignAvailableRoom(stable));
        shelterHorseRepository.save(shelterHorse);
    }

    public List<GroomingRequest> requestGroomingService(Integer horseOwnerId, GroomingRequest request) {
        // check valid owner id
        HorseOwner horseOwner = horseOwnerRepository.findHorseOwnerById(horseOwnerId);
        if (horseOwner == null) {
            throw new ApiException("Horse Owner ID Not Found");
        }
        // creat the request
//        GroomingRequest groomingRequest = new GroomingRequest();
//        groomingRequest.setServiceName(request.getServiceName());
//        groomingRequest.setIsAccepted(false); // Default value
        request.setHorseOwnerId(horseOwnerId); // Set owner ID
        request.setIsAccepted(false);         // Default to not accepted
        groomingRequestService.addGroomingRequest(request);

        // n save it
//        groomingRequestService.addGroomingRequest(groomingRequest);
//        return groomingRequestService.getGroomingRequests();
        return groomingRequestService.getGroomingRequests();
    }

    public List<GroomingRequest> getAllAcceptedGroomingRequestsByOwner(Integer horseOwnerId) {
        List<GroomingRequest> acceptedRequests = new ArrayList<>();
        for (GroomingRequest request : groomingRequestService.getGroomingRequests()) {
            if (request.getIsAccepted() && horseOwnerId.equals(request.getHorseOwnerId())) {
                acceptedRequests.add(request);
            }
        }
        return acceptedRequests;
    }


}
