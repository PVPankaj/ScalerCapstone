package com.scaler.capstone.jobportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling notification-related operations.
 */
@RestController
@CrossOrigin
@RequestMapping("/notification")
@Validated
public class NotificationController {

    /**
     * Retrieves all notifications for a specific user.
     *
     * @param userId the ID of the user whose notifications are to be fetched
     * @return a list of notifications with HTTP status 200
     */
    @GetMapping("/get/{userId}")
    public ResponseEntity<List<String>> getNotifications(@PathVariable Long userId) {
        return new ResponseEntity<>(List.of("Notification1", "Notification2", "Notification3"), HttpStatus.OK);
    }

    /**
     * Marks a specific notification as read.
     *
     * @param id the ID of the notification to mark as read
     * @return a confirmation message with HTTP status 200
     */
    @PutMapping("/read/{id}")
    public ResponseEntity<String> readNotification(@PathVariable Long id) {
        return new ResponseEntity<>("Notification1", HttpStatus.OK);
    }
}
