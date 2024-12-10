package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.Model.Horse;
import com.example.stablemanagementsystem.Model.ShelterHorse;
import com.example.stablemanagementsystem.Model.Stable;
import com.example.stablemanagementsystem.Repository.HorseRepository;
import com.example.stablemanagementsystem.Repository.ShelterHorseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShelterHorseService {
    private final ShelterHorseRepository shelterHorseRepository;
    private final HorseRepository horseRepository;

    public List<ShelterHorse> getAllShelteredHorses(){
       return shelterHorseRepository.findAll();
    }

    public ShelterHorse getShelteredHorseById(Integer id){
        ShelterHorse horse = shelterHorseRepository.findShelterHorseById(id);
        return horse;
    }

    public Integer getShelterPriceBasedOnBreed(String breed){
        Integer price = 0;
        if(breed.equalsIgnoreCase("arabic")){
            price = 1000;
        } else if(breed.equalsIgnoreCase("hybrid")){
            price = 1500;
        } else if(breed.equalsIgnoreCase("english")){
            price = 2000;
        }
        return price;
    }

    public Integer assignAvailableRoom(Stable stable){
        List<ShelterHorse> shelterHorses = shelterHorseRepository.findAll();
        List<Horse> ridingHorses = horseRepository.findAll();
        for (int i = 0; i <= stable.getNumberOfRooms(); i++) {
            Boolean isRoomTaken = false; //flag to check the rooms list

            // check in sheltered horses list
            for(ShelterHorse sh : shelterHorses){
                // if its room number not empty and it equals the value of i
                if(sh.getRoomNumber() != null && sh.getRoomNumber().equals(i)){
                    isRoomTaken = true;
                    break;
                }
            }

            // check in riding horses list
            for(Horse rh : ridingHorses){
                if(rh.getRoomnumber() != null && rh.getRoomnumber().equals(i)){
                    isRoomTaken = true;
                    break;
                }
            }

            if(!isRoomTaken){
                return i;
            }

        }
        return null; //no available room found
    }
}
