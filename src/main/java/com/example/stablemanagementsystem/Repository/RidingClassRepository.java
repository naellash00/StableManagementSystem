package com.example.stablemanagementsystem.Repository;

import com.example.stablemanagementsystem.Model.RidingClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RidingClassRepository extends JpaRepository<RidingClass, Integer> {
    RidingClass findRidingClassById(Integer id);

    @Query("select count(r) > 0 from RidingClass r where r.date = ?1")
    Boolean existsByDate(LocalDateTime date);

    @Query("select sum(t.numberoftrainees) from RidingClass t WHERE t.date = ?1")
    Integer getNumberOfTraineesByClassDate(LocalDateTime date);

    @Query("select r from RidingClass r where r.date = ?1")
    RidingClass findByDate(LocalDateTime date);


}
