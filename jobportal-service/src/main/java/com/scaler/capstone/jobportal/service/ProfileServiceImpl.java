package com.scaler.capstone.jobportal.service;

import com.scaler.capstone.jobportal.dto.request.ProfileRequestDTO;
import com.scaler.capstone.jobportal.dto.request.UserRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ProfileResponseDTO;
import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.mapper.ProfileMapper;
import com.scaler.capstone.jobportal.model.Profile;
import com.scaler.capstone.jobportal.repository.ProfileRepository;
import com.scaler.capstone.jobportal.util.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;

    /**
     * Creates a new profile from the given user details.
     *
     * @param userDTO the user request DTO containing email and name
     * @return the generated profile ID
     * @throws JobPortalException if profile creation fails
     */
    @Override
    public Long createProfile(UserRequestDTO userDTO) throws JobPortalException {
        Profile profile = new Profile();
        profile.setId(MongoUtil.getNextSequenceId("profiles"));
        profile.setEmail(userDTO.getEmail());
        profile.setName(userDTO.getName());
        profile.setSkills(new ArrayList<>());
        profile.setExperiences(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        profileRepository.save(profile);
        return profile.getId();
    }

    /**
     * Retrieves the profile with the given ID.
     *
     * @param id the profile ID
     * @return the profile response DTO
     * @throws JobPortalException if profile is not found
     */
    @Override
    public ProfileResponseDTO getProfile(Long id) throws JobPortalException {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new JobPortalException("PROFILE_NOT_FOUND"));
        return profileMapper.toResponseDto(profile);
    }

    /**
     * Updates an existing profile with the given profile details.
     *
     * @param profileDTO the profile request DTO containing updated data
     * @return the updated profile response DTO
     * @throws JobPortalException if profile is not found
     */
    @Override
    public ProfileResponseDTO updateProfile(ProfileRequestDTO profileDTO) throws JobPortalException {
        profileRepository.findById(profileDTO.getId()).orElseThrow(() -> new JobPortalException("PROFILE_NOT_FOUND"));
        Profile profile = profileMapper.toModel(profileDTO);
        profileRepository.save(profile);
        return profileMapper.toResponseDto(profile);
    }

    /**
     * Retrieves all profiles from the database.
     *
     * @return a list of all profile response DTOs
     * @throws JobPortalException if any error occurs during retrieval
     */
    @Override
    public List<ProfileResponseDTO> getAllProfiles() throws JobPortalException {
        return profileRepository.findAll().stream().map((x) -> profileMapper.toResponseDto(x)).toList();
    }


}
