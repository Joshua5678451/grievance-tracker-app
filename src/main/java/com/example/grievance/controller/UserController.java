package com.example.grievance.controller;

import com.example.grievance.model.User;
import com.example.grievance.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    // Automatically create the Admin user when the server starts
    @PostConstruct
    public void createAdmin() {
        if (repository.findByUsername("admin@12") == null) {
            repository.save(new User("admin@12", "admin@123", "ADMIN"));
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        if (repository.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }
        user.setRole("CITIZEN"); // Default role
        repository.save(user);
        return "Signup successful!";
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User existingUser = repository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return existingUser; // Login Success
        }
        throw new RuntimeException("Invalid username or password");
    }
}
