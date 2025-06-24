package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.ProfileRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ProfileResponseDTO;
import com.scaler.capstone.jobportal.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Profile model and DTOs using MapStruct.
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {

    /**
     * Singleton instance of the mapper (used when not injecting via Spring).
     */
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    /**
     * Converts a ProfileRequestDTO to a Profile model.
     *
     * @param dto the ProfileRequestDTO object
     * @return the Profile model
     */
    Profile toModel(ProfileRequestDTO dto);

    /**
     * Converts a ProfileResponseDTO to a Profile model.
     *
     * @param dto the ProfileResponseDTO object
     * @return the Profile model
     */
    Profile toModel(ProfileResponseDTO dto);

    /**
     * Converts a Profile model to a ProfileRequestDTO.
     *
     * @param profile the Profile model
     * @return the ProfileRequestDTO
     */
    ProfileRequestDTO toRequestDto(Profile profile);

    /**
     * Converts a Profile model to a ProfileResponseDTO.
     *
     * @param profile the Profile model
     * @return the ProfileResponseDTO
     */
    ProfileResponseDTO toResponseDto(Profile profile);
}
