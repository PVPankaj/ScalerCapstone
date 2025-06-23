package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.ProfileRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ProfileResponseDTO;
import com.scaler.capstone.jobportal.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    Profile toModel(ProfileRequestDTO dto);

    Profile toModel(ProfileResponseDTO dto);

    ProfileRequestDTO toRequestDto(Profile profile);

    ProfileResponseDTO toResponseDto(Profile profile);
}
