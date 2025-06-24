package com.scaler.capstone.jobportal.controller;

import com.scaler.capstone.jobportal.dto.request.ApplicantRequestDTO;
import com.scaler.capstone.jobportal.dto.request.JobRequestDTO;
import com.scaler.capstone.jobportal.dto.response.JobResponseDTO;
import com.scaler.capstone.jobportal.dto.response.ResponseMessageDTO;
import com.scaler.capstone.jobportal.enums.ApplicationStatus;
import com.scaler.capstone.jobportal.exception.AlreadyAppliedForJobException;
import com.scaler.capstone.jobportal.exception.InvalidApplicationStatusException;
import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.exception.ResourceNotFoundException;
import com.scaler.capstone.jobportal.mapper.JobMapper;
import com.scaler.capstone.jobportal.model.Application;
import com.scaler.capstone.jobportal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/jobs")
@Validated
public class JobController {
    @Autowired
    private JobService jobService;

    @Autowired
    private JobMapper jobMapper;

    /**
     * Posts a new job.
     *
     * @param jobDTO the job details to be posted
     * @return the created job as a JobResponseDTO with HTTP status 201
     * @throws JobPortalException if any error occurs while posting the job
     */
    @PostMapping("/post")
    public ResponseEntity<JobResponseDTO> postJob(@RequestBody @Valid JobRequestDTO jobDTO) throws JobPortalException {
        return new ResponseEntity<>(jobService.postJob(jobDTO), HttpStatus.CREATED);
    }


    /**
     * Posts multiple jobs.
     *
     * @param jobDTOs list of job details to be posted
     * @return list of created jobs as JobResponseDTOs with HTTP status 201
     * @throws JobPortalException if any error occurs while posting jobs
     */
    @PostMapping("/postAll")
    public ResponseEntity<List<JobResponseDTO>> postAllJob(@RequestBody @Valid List<JobRequestDTO> jobDTOs) throws JobPortalException {

        return new ResponseEntity<>(jobDTOs.stream().map(x-> {
            try {
                return jobService.postJob(x);
            } catch (JobPortalException e) {
                throw new RuntimeException(e);
            }
        }).toList(), HttpStatus.CREATED);
    }

    /**
     * Retrieves all available jobs.
     *
     * @return list of all JobResponseDTOs with HTTP status 200
     * @throws JobPortalException if any error occurs while retrieving jobs
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() throws JobPortalException {
        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    /**
     * Retrieves a specific job by ID.
     *
     * @param id the ID of the job to retrieve
     * @return the job details as JobResponseDTO with HTTP status 200
     * @throws JobPortalException if any general error occurs
     * @throws ResourceNotFoundException if the job is not found
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<JobResponseDTO> getJob(@PathVariable Long id) throws JobPortalException,ResourceNotFoundException {
        return new ResponseEntity<>(jobService.getJob(id), HttpStatus.OK);
    }

    /**
     * Applies for a job using the given applicant details.
     *
     * @param id the ID of the job to apply for
     * @param applicantDTO the applicant's details
     * @return success message with HTTP status 200
     * @throws ResourceNotFoundException if the job is not found
     * @throws AlreadyAppliedForJobException if the applicant has already applied
     * @throws JobPortalException if any other error occurs
     */
    @PostMapping("apply/{id}")
    public ResponseEntity<ResponseMessageDTO> applyJob(@PathVariable Long id, @RequestBody ApplicantRequestDTO applicantDTO) throws ResourceNotFoundException, AlreadyAppliedForJobException, JobPortalException {
        jobService.applyJob(id, applicantDTO);
        return new ResponseEntity<>(new ResponseMessageDTO("Applied Successfully"), HttpStatus.OK);
    }

    /**
     * Retrieves jobs posted by a specific user.
     *
     * @param id the ID of the user
     * @return list of JobResponseDTOs posted by the user with HTTP status 200
     * @throws JobPortalException if any error occurs while retrieving jobs
     */
    @GetMapping("/postedBy/{id}")
    public ResponseEntity<List<JobResponseDTO>> getJobsPostedBy(@PathVariable Long id) throws JobPortalException {
        return new ResponseEntity<>(jobService.getJobsPostedBy(id), HttpStatus.OK);
    }

    /**
     * Retrieves job application history by user ID and application status.
     *
     * @param id the ID of the user
     * @param applicationStatus the status of applications to filter
     * @return list of JobResponseDTOs with the specified status and user ID with HTTP status 200
     * @throws JobPortalException if any error occurs while retrieving history
     */
    @GetMapping("/history/{id}/{applicationStatus}")
    public ResponseEntity<List<JobResponseDTO>> getHistory(@PathVariable Long id, @PathVariable ApplicationStatus applicationStatus) throws JobPortalException {
        return new ResponseEntity<>(jobService.getHistory(id, applicationStatus), HttpStatus.OK);
    }

    /**
     * Changes the status of a job application.
     *
     * @param application the application object with updated status
     * @return success message with HTTP status 200
     * @throws JobPortalException if any general error occurs
     * @throws InvalidApplicationStatusException if the new status is invalid
     */
    @PostMapping("/changeAppStatus")
    public ResponseEntity<ResponseMessageDTO> changeAppStatus(@RequestBody Application application) throws JobPortalException, InvalidApplicationStatusException {
        jobService.changeAppStatus(application);
        return new ResponseEntity<>(new ResponseMessageDTO("Status Changed Successfully"), HttpStatus.OK);
    }
}
