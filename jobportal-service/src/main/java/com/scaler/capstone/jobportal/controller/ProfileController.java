package com.scaler.capstone.jobportal.controller;

import com.scaler.capstone.jobportal.dto.request.ProfileRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ProfileResponseDTO;
import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling profile-related API endpoints such as
 * retrieving, updating, and listing user profiles.
 */
@RestController
@CrossOrigin
@RequestMapping("/profiles")
@Validated
public class ProfileController {

    /** Service to handle profile operations. */
    @Autowired
    private ProfileService profileService;

    /**
     * Retrieves a user profile by its ID.
     *
     * @param id the ID of the profile
     * @return the profile response DTO
     * @throws JobPortalException if profile is not found
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable Long id) throws JobPortalException {
        return new ResponseEntity<>(profileService.getProfile(id), HttpStatus.OK);
    }

    /**
     * Retrieves all user profiles.
     *
     * @return a list of profile response DTOs
     * @throws JobPortalException if an error occurs while fetching profiles
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() throws JobPortalException {
        return new ResponseEntity<>(profileService.getAllProfiles(), HttpStatus.OK);
    }

    /**
     * Updates an existing user profile.
     *
     * @param profileDTO the updated profile information
     * @return the updated profile response DTO
     * @throws JobPortalException if the update fails
     */
    @PutMapping("/update")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@RequestBody ProfileRequestDTO profileDTO) throws JobPortalException {
        return new ResponseEntity<>(profileService.updateProfile(profileDTO), HttpStatus.OK);
    }

}
