package com.scaler.capstone.jobportal.service;

import com.scaler.capstone.jobportal.dto.request.ApplicantRequestDTO;
import com.scaler.capstone.jobportal.dto.request.JobRequestDTO;
import com.scaler.capstone.jobportal.dto.response.JobResponseDTO;
import com.scaler.capstone.jobportal.enums.ApplicationStatus;
import com.scaler.capstone.jobportal.exception.AlreadyAppliedForJobException;
import com.scaler.capstone.jobportal.exception.InvalidApplicationStatusException;
import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.exception.ResourceNotFoundException;
import com.scaler.capstone.jobportal.model.Application;

import java.util.List;


public interface JobService {

    JobResponseDTO postJob(JobRequestDTO jobRequestDTO) throws JobPortalException;

    List<JobResponseDTO> getAllJobs() throws JobPortalException;

    JobResponseDTO getJob(Long id) throws JobPortalException, ResourceNotFoundException;

    void applyJob(Long id, ApplicantRequestDTO applicantRequestDTO) throws JobPortalException, ResourceNotFoundException, AlreadyAppliedForJobException;

    List<JobResponseDTO> getHistory(Long id, ApplicationStatus applicationStatus);

    List<JobResponseDTO> getJobsPostedBy(Long id) throws JobPortalException;

    void changeAppStatus(Application application) throws InvalidApplicationStatusException, JobPortalException, InvalidApplicationStatusException;


}
