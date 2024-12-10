package com.example.stablemanagementsystem.Repository;

import com.example.stablemanagementsystem.Model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
    Trainee findTraineeById(Integer id);
}
