package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.ApplicantRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ApplicantResponseDTO;
import com.scaler.capstone.jobportal.model.Applicant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Applicant model and DTOs using MapStruct.
 */
@Mapper(componentModel = "spring")
public interface ApplicantMapper {

    /**
     * Singleton instance (used when not injecting via Spring).
     */
    ApplicantMapper INSTANCE = Mappers.getMapper(ApplicantMapper.class);

    /**
     * Converts ApplicantRequestDTO to Applicant model.
     *
     * @param dto the request DTO
     * @return the Applicant model
     */
    Applicant toModel(ApplicantRequestDTO dto);

    /**
     * Converts ApplicantResponseDTO to Applicant model.
     *
     * @param dto the response DTO
     * @return the Applicant model
     */
    Applicant toModel(ApplicantResponseDTO dto);

    /**
     * Converts Applicant model to ApplicantRequestDTO.
     *
     * @param applicant the model
     * @return the request DTO
     */
    ApplicantRequestDTO toRequestDto(Applicant applicant);

    /**
     * Converts Applicant model to ApplicantResponseDTO.
     *
     * @param applicant the model
     * @return the response DTO
     */
    ApplicantResponseDTO toResponseDto(Applicant applicant);
}