package com.scaler.capstone.jobportal.controller;

import com.scaler.capstone.jobportal.dto.request.ApplicantRequestDTO;
import com.scaler.capstone.jobportal.dto.request.JobRequestDTO;
import com.scaler.capstone.jobportal.dto.response.JobResponseDTO;
import com.scaler.capstone.jobportal.dto.response.ResponseMessageDTO;
import com.scaler.capstone.jobportal.enums.ApplicationStatus;
import com.scaler.capstone.jobportal.exception.JobPortalException;
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

    @PostMapping("/post")
    public ResponseEntity<JobResponseDTO> postJob(@RequestBody @Valid JobRequestDTO jobDTO) throws JobPortalException {
        return new ResponseEntity<>(jobService.postJob(jobDTO), HttpStatus.CREATED);
    }


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

    @GetMapping("/getAll")
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() throws JobPortalException {
        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobResponseDTO> getJob(@PathVariable Long id) throws JobPortalException {
        return new ResponseEntity<>(jobService.getJob(id), HttpStatus.OK);
    }

    @PostMapping("apply/{id}")
    public ResponseEntity<ResponseMessageDTO> applyJob(@PathVariable Long id, @RequestBody ApplicantRequestDTO applicantDTO) throws JobPortalException {
        jobService.applyJob(id, applicantDTO);
        return new ResponseEntity<>(new ResponseMessageDTO("Applied Successfully"), HttpStatus.OK);
    }

    @GetMapping("/postedBy/{id}")
    public ResponseEntity<List<JobResponseDTO>> getJobsPostedBy(@PathVariable Long id) throws JobPortalException {
        return new ResponseEntity<>(jobService.getJobsPostedBy(id), HttpStatus.OK);
    }

    @GetMapping("/history/{id}/{applicationStatus}")
    public ResponseEntity<List<JobResponseDTO>> getHistory(@PathVariable Long id, @PathVariable ApplicationStatus applicationStatus) throws JobPortalException {
        return new ResponseEntity<>(jobService.getHistory(id, applicationStatus), HttpStatus.OK);
    }

    @PostMapping("/changeAppStatus")
    public ResponseEntity<ResponseMessageDTO> changeAppStatus(@RequestBody Application application) throws JobPortalException {
        jobService.changeAppStatus(application);
        return new ResponseEntity<>(new ResponseMessageDTO("Status Chhanged Successfully"), HttpStatus.OK);
    }
}
