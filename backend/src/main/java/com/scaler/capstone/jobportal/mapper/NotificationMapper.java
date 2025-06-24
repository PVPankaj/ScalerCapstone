package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.NotificationRequestDTO;
import com.scaler.capstone.jobportal.dto.response.NotificationResponseDTO;
import com.scaler.capstone.jobportal.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Notification model and DTOs using MapStruct.
 */
@Mapper(componentModel = "spring")
public interface NotificationMapper {

    /**
     * Singleton instance of the mapper (used when not injecting via Spring).
     */
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    /**
     * Converts a NotificationRequestDTO to a Notification model.
     *
     * @param dto the NotificationRequestDTO object
     * @return the Notification model
     */
    Notification toModel(NotificationRequestDTO dto);

    /**
     * Converts a NotificationResponseDTO to a Notification model.
     *
     * @param dto the NotificationResponseDTO object
     * @return the Notification model
     */
    Notification toModel(NotificationResponseDTO dto);

    /**
     * Converts a Notification model to a NotificationRequestDTO.
     *
     * @param notification the Notification model
     * @return the NotificationRequestDTO
     */
    NotificationRequestDTO toRequestDto(Notification notification);

    /**
     * Converts a Notification model to a NotificationResponseDTO.
     *
     * @param notification the Notification model
     * @return the NotificationResponseDTO
     */
    NotificationResponseDTO toResponseDto(Notification notification);
}
