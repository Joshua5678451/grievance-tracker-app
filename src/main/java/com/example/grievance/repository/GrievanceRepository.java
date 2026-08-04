package com.example.grievance.repository;

import com.example.grievance.model.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    // New trick: Find complaints belonging to a specific person
    List<Grievance> findByCitizenName(String citizenName);
}
