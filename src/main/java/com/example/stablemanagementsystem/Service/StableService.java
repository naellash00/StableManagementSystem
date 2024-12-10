package com.example.stablemanagementsystem.Service;

import com.example.stablemanagementsystem.Model.Stable;
import com.example.stablemanagementsystem.Repository.StableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StableService {
    private final StableRepository stableRepository;

    public void addStable(Stable stable){
        stableRepository.save(stable);
    }
    public Stable getStableInformation(Integer id){
        return stableRepository.findStableById(id);
    }
}
