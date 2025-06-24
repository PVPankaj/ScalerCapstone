package com.scaler.capstone.jobportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notification")
@Validated
public class NotificationController {

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<String>> getNotifications(@PathVariable Long userId) {
        return new ResponseEntity<>(List.of("Notification1", "Notification2", "Notification3"), HttpStatus.OK);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<String> readNotification(@PathVariable Long id) {
        return new ResponseEntity<>("Notification1", HttpStatus.OK);
    }
}
