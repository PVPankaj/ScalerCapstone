package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.UserRequestDTO;
import com.scaler.capstone.jobportal.dto.response.UserResponseDTO;
import com.scaler.capstone.jobportal.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserRequestDTO dto);

    User toModel(UserResponseDTO dto);

    UserRequestDTO toRequestDto(User user);

    UserResponseDTO toResponseDto(User user);

}
