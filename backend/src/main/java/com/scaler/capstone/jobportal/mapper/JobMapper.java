package com.scaler.capstone.jobportal.mapper;

import com.scaler.capstone.jobportal.dto.request.ApplicantRequestDTO;
import com.scaler.capstone.jobportal.dto.request.JobRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ApplicantResponseDTO;
import com.scaler.capstone.jobportal.dto.response.JobResponseDTO;
import com.scaler.capstone.jobportal.model.Applicant;
import com.scaler.capstone.jobportal.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    Job toModel(JobRequestDTO dto);

    Job toModel(JobResponseDTO dto);

    JobRequestDTO toRequestDto(Job job);

    JobResponseDTO toResponseDto(Job job);
}
