package com.example.grievance.service;

import com.example.grievance.model.Grievance;
import com.example.grievance.repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GrievanceService {
    @Autowired
    private GrievanceRepository repository;

    public Grievance fileGrievance(Grievance grievance) {
        return repository.save(grievance);
    }

    public List<Grievance> getAllGrievances() {
        return repository.findAll();
    }

    // New trick: Get only the grievances for one user
    public List<Grievance> getGrievancesByUser(String name) {
        return repository.findByCitizenName(name);
    }

    public Grievance updateStatus(Long id, String newStatus) {
        Grievance grievance = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grievance not found"));
        grievance.setStatus(newStatus);
        return repository.save(grievance);
    }
    // New trick: Delete a grievance
    public void deleteGrievance(Long id) {
        repository.deleteById(id);
    }
}