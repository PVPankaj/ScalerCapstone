package com.scaler.capstone.jobportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/profiles")
@Validated
public class ProfileController {
    @GetMapping("/get/{id}")
    public ResponseEntity<String> getProfile(@PathVariable Long id) {
        return new ResponseEntity<>("Profile1", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<String>> getAllProfiles() {
        return new ResponseEntity<>(List.of("profile1", "profile2", "profile3"), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody String profileData) {
        return new ResponseEntity<>("profile data updated!", HttpStatus.OK);
    }

}
