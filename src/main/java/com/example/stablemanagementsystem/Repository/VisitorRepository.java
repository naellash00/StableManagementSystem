package com.example.stablemanagementsystem.Repository;

import com.example.stablemanagementsystem.Model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
    Visitor findVisitorById(Integer id);
    Visitor findVisitorByUsername(String username);
}
