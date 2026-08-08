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

    @GetMapping("/user/{name}")
    public List<Grievance> getUserGrievances(@PathVariable String name) {
        return service.getGrievancesByUser(name);
    }

    // Upgraded web link to catch the admin note
    @PutMapping("/{id}/status")
    public Grievance updateStatus(@PathVariable Long id, @RequestParam String status, @RequestParam(required = false) String adminNote) {
        return service.updateStatus(id, status, adminNote);
    }

    @DeleteMapping("/{id}")
    public void deleteGrievance(@PathVariable Long id) {
        service.deleteGrievance(id);
    }
}