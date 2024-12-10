package com.example.stablemanagementsystem.Repository;

import com.example.stablemanagementsystem.Model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {
    Coach findCoachById(Integer id);
}
