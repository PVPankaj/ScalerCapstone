package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.NotificationRequestDTO;
import com.scaler.capstone.jobportal.dto.response.NotificationResponseDTO;
import com.scaler.capstone.jobportal.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    Notification toModel(NotificationRequestDTO dto);

    Notification toModel(NotificationResponseDTO dto);

    NotificationRequestDTO toRequestDto(Notification notification);

    NotificationResponseDTO toResponseDto(Notification notification);
}
