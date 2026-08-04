package com.example.grievance.controller;

import com.example.grievance.model.Grievance;
import com.example.grievance.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/grievances")
public class GrievanceController {
    @Autowired
    private GrievanceService service;

    @PostMapping
    public Grievance submitGrievance(@RequestBody Grievance grievance) {
        return service.fileGrievance(grievance);
    }

    @GetMapping
    public List<Grievance> getAll() {
        return service.getAllGrievances();
    }

    // New web link to fetch a user's specific history
    @GetMapping("/user/{name}")
    public List<Grievance> getUserGrievances(@PathVariable String name) {
        return service.getGrievancesByUser(name);
    }

    @PutMapping("/{id}/status")
    public Grievance updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }
    // New web link to delete a record
    @DeleteMapping("/{id}")
    public void deleteGrievance(@PathVariable Long id) {
        service.deleteGrievance(id);
    }
}