package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.JobRequestDTO;
import com.scaler.capstone.jobportal.dto.response.JobResponseDTO;
import com.scaler.capstone.jobportal.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Job model and DTOs using MapStruct.
 */
@Mapper(componentModel = "spring")
public interface JobMapper {

    /**
     * Singleton instance of the mapper (used when not injecting via Spring).
     */
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    /**
     * Converts a JobRequestDTO to a Job model.
     *
     * @param dto the JobRequestDTO object
     * @return the Job model
     */
    Job toModel(JobRequestDTO dto);

    /**
     * Converts a JobResponseDTO to a Job model.
     *
     * @param dto the JobResponseDTO object
     * @return the Job model
     */
    Job toModel(JobResponseDTO dto);

    /**
     * Converts a Job model to a JobRequestDTO.
     *
     * @param job the Job model
     * @return the JobRequestDTO
     */
    JobRequestDTO toRequestDto(Job job);

    /**
     * Converts a Job model to a JobResponseDTO.
     *
     * @param job the Job model
     * @return the JobResponseDTO
     */
    JobResponseDTO toResponseDto(Job job);
}
