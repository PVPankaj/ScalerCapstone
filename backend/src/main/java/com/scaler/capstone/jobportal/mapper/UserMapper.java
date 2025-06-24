package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.UserRequestDTO;
import com.scaler.capstone.jobportal.dto.response.UserResponseDTO;
import com.scaler.capstone.jobportal.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between User model and DTOs using MapStruct.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Singleton instance of the mapper (used when not injecting via Spring).
     */
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Converts a UserRequestDTO to a User model.
     *
     * @param dto the UserRequestDTO object
     * @return the User model
     */
    User toModel(UserRequestDTO dto);

    /**
     * Converts a UserResponseDTO to a User model.
     *
     * @param dto the UserResponseDTO object
     * @return the User model
     */
    User toModel(UserResponseDTO dto);

    /**
     * Converts a User model to a UserRequestDTO.
     *
     * @param user the User model
     * @return the UserRequestDTO
     */
    UserRequestDTO toRequestDto(User user);

    /**
     * Converts a User model to a UserResponseDTO.
     *
     * @param user the User model
     * @return the UserResponseDTO
     */
    UserResponseDTO toResponseDto(User user);
}
