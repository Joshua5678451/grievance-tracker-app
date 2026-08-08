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

    public List<Grievance> getGrievancesByUser(String name) {
        return repository.findByCitizenName(name);
    }

    // Upgraded trick: Save the status AND the admin's note
    public Grievance updateStatus(Long id, String newStatus, String adminNote) {
        Grievance grievance = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grievance not found"));
        grievance.setStatus(newStatus);
        grievance.setAdminNote(adminNote);
        return repository.save(grievance);
    }

    public void deleteGrievance(Long id) {
        repository.deleteById(id);
    }
}