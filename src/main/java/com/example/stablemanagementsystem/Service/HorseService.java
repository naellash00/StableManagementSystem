package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.ApiResponse.ApiException;
import com.example.stablemanagementsystem.Model.Horse;
import com.example.stablemanagementsystem.Model.Stable;
import com.example.stablemanagementsystem.Repository.HorseRepository;
import com.example.stablemanagementsystem.Repository.StableRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HorseService {
    private final HorseRepository horseRepository;
    private final StableRepository stableRepository;


    public List<Horse> getAllHorses() {
        List<Horse> horses = horseRepository.findAll();
        return horses;
    }

    public void addHorse(Horse horse) {
        horseRepository.save(horse);

        Stable stable = stableRepository.findById(1).orElse(null);
        if (stable == null) {
            throw new ApiException("Stable Not Found");
        }
        // decrease number of rooms everytime new horse is created
        stable.setNumberOfRooms(stable.getNumberOfRooms() - 1);
        //svae the update in repo
        stableRepository.save(stable);
    }

    public void updateHorse(Integer id, Horse horse) {
        Horse oldHorse = horseRepository.findHorseById(id);
        if (oldHorse == null) {
            throw new ApiException("Horse ID Not Found");
        }
        oldHorse.setName(horse.getName());
        oldHorse.setBreed(horse.getBreed());
        oldHorse.setAge(horse.getAge());
        oldHorse.setRoomnumber(horse.getRoomnumber());
        oldHorse.setStatus(horse.getStatus());
        horseRepository.save(oldHorse);
    }

    public void deleteHorse(Integer id) {
        Horse horse = horseRepository.findHorseById(id);
        if (horse == null) {
            throw new ApiException("Horse ID Not Found");
        }
        horseRepository.delete(horse);
    }
}
