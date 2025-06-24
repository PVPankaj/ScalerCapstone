package com.scaler.capstone.jobportal.service;

import com.scaler.capstone.jobportal.dto.request.ProfileRequestDTO;
import com.scaler.capstone.jobportal.dto.request.UserRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ProfileResponseDTO;
import com.scaler.capstone.jobportal.exception.JobPortalException;

import java.util.List;

public interface ProfileService {

    Long createProfile(UserRequestDTO userRequestDTO) throws JobPortalException;

    ProfileResponseDTO getProfile(Long id) throws JobPortalException;

    ProfileResponseDTO updateProfile(ProfileRequestDTO profileRequestDTO) throws JobPortalException;

    List<ProfileResponseDTO> getAllProfiles() throws JobPortalException;
}
