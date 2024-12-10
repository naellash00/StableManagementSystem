package com.example.stablemanagementsystem.Repository;

import com.example.stablemanagementsystem.Model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    Subscription findSubscriptionById(Integer id);

//    @Query("select p from Subscription p where p.type = ?1")
//    Integer getPriceByType(String type);
    @Query("select s from Subscription s where s.traineeid =?1")
    Subscription getSubscriptionByTraineeId(Integer traineeID);
}
