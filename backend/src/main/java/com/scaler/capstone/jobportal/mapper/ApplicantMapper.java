package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.ApplicantRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ApplicantResponseDTO;
import com.scaler.capstone.jobportal.model.Applicant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {
    ApplicantMapper INSTANCE = Mappers.getMapper(ApplicantMapper.class);

    Applicant toModel(ApplicantRequestDTO dto);

    Applicant toModel(ApplicantResponseDTO dto);

    ApplicantRequestDTO toRequestDto(Applicant applicant);

    ApplicantResponseDTO toResponseDto(Applicant applicant);
}