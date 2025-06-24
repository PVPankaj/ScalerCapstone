package com.scaler.capstone.jobportal.service;

import com.scaler.capstone.jobportal.dto.request.ApplicantRequestDTO;
import com.scaler.capstone.jobportal.dto.request.JobRequestDTO;
import com.scaler.capstone.jobportal.dto.request.NotificationRequestDTO;
import com.scaler.capstone.jobportal.dto.response.JobResponseDTO;
import com.scaler.capstone.jobportal.enums.ApplicationStatus;
import com.scaler.capstone.jobportal.enums.JobStatus;
import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.mapper.ApplicantMapper;
import com.scaler.capstone.jobportal.mapper.JobMapper;
import com.scaler.capstone.jobportal.model.Applicant;
import com.scaler.capstone.jobportal.model.Application;
import com.scaler.capstone.jobportal.model.Job;
import com.scaler.capstone.jobportal.repository.JobRepository;
import com.scaler.capstone.jobportal.util.MongoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the JobService interface.
 * Handles job posting, fetching, application, and application status updates.
 */
@Service("jobService")
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private ApplicantMapper applicantMapper;

    /**
     * Posts a new job or updates an existing one.
     *
     * @param jobDTO the job request data
     * @return the saved job response data
     * @throws JobPortalException if any job processing error occurs
     */
    @Override
    public JobResponseDTO postJob(JobRequestDTO jobDTO) throws JobPortalException {
        if (jobDTO.getId() == 0) {
            jobDTO.setId(MongoUtil.getNextSequenceId("jobs"));
            jobDTO.setPostTime(LocalDateTime.now());
            NotificationRequestDTO notiDto = new NotificationRequestDTO();
            notiDto.setAction("Job Posted");
            notiDto.setMessage("Job Posted Successfully for " + jobDTO.getJobTitle() + " at " + jobDTO.getCompany());

            notiDto.setUserId(jobDTO.getPostedBy());
            notiDto.setRoute("/posted-jobs/" + jobDTO.getId());
            notificationService.sendNotification(notiDto);
        } else {
            Job job = jobRepository.findById(jobDTO.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
            if (job.getJobStatus().equals(JobStatus.DRAFT) || jobDTO.getJobStatus().equals(JobStatus.CLOSED))
                jobDTO.setPostTime(LocalDateTime.now());
        }
        Job job = jobMapper.toModel(jobDTO);
        jobRepository.save(job);
        return jobMapper.toResponseDto(job);
    }


    /**
     * Retrieves all jobs from the database.
     *
     * @return list of job response DTOs
     * @throws JobPortalException if any error occurs during fetch
     */
    @Override
    public List<JobResponseDTO> getAllJobs() throws JobPortalException {
        return jobRepository.findAll().stream().map((x) -> jobMapper.toResponseDto(x)).toList();
    }

    /**
     * Retrieves a job by its ID.
     *
     * @param id the job ID
     * @return the job response DTO
     * @throws JobPortalException if job is not found
     */
    @Override
    public JobResponseDTO getJob(Long id) throws JobPortalException {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
        return jobMapper.toResponseDto(job);
    }

    /**
     * Applies for a job by adding a new applicant to the job.
     *
     * @param id           the job ID
     * @param applicantDTO the applicant request data
     * @throws JobPortalException if job not found or already applied
     */
    @Override
    public void applyJob(Long id, ApplicantRequestDTO applicantDTO) throws JobPortalException {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
        List<Applicant> applicants = job.getApplicants();
        if (applicants == null) applicants = new ArrayList<>();
        if (applicants.stream().filter((x) -> x.getApplicantId() == applicantDTO.getApplicantId()).toList().size() > 0)
            throw new JobPortalException("JOB_APPLIED_ALREADY");
        applicantDTO.setApplicationStatus(ApplicationStatus.APPLIED);
        applicants.add(applicantMapper.toModel(applicantDTO));
        job.setApplicants(applicants);
        jobRepository.save(job);
    }

    /**
     * Retrieves job history for a given applicant and application status.
     *
     * @param id                the applicant ID
     * @param applicationStatus the application status to filter by
     * @return list of job response DTOs
     */
    @Override
    public List<JobResponseDTO> getHistory(Long id, ApplicationStatus applicationStatus) {
        return jobRepository.findByApplicantIdAndApplicationStatus(id, applicationStatus).stream().map((x) -> jobMapper.toResponseDto(x))
                .toList();
    }

    /**
     * Retrieves jobs posted by a specific user.
     *
     * @param id the ID of the user who posted the jobs
     * @return list of job response DTOs
     * @throws JobPortalException if any error occurs during fetch
     */
    @Override
    public List<JobResponseDTO> getJobsPostedBy(Long id) throws JobPortalException {
        return jobRepository.findByPostedBy(id).stream().map((x) -> jobMapper.toResponseDto(x)).toList();
    }


    /**
     * Changes the application status for a given application.
     * Also sends a notification if the status is INTERVIEWING.
     *
     * @param application the application with updated status
     * @throws JobPortalException if job not found
     */
    @Override
    public void changeAppStatus(Application application) throws JobPortalException {
        Job job = jobRepository.findById(application.getId()).orElseThrow(() -> new JobPortalException("JOB_NOT_FOUND"));
        List<Applicant> apps = job.getApplicants().stream().map((x) -> {
            if (application.getApplicantId() == x.getApplicantId()) {
                x.setApplicationStatus(application.getApplicationStatus());
                if (application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) {
                    x.setInterviewTime(application.getInterviewTime());
                    NotificationRequestDTO notiDto = new NotificationRequestDTO();
                    notiDto.setAction("Interview Scheduled");
                    notiDto.setMessage("Interview scheduled for job id: " + application.getId());
                    notiDto.setUserId(application.getApplicantId());
                    notiDto.setRoute("/job-history");
                    try {
                        notificationService.sendNotification(notiDto);
                    } catch (JobPortalException e) {
                        e.printStackTrace();
                    }
                }
            }
            return x;
        }).toList();
        job.setApplicants(apps);
        jobRepository.save(job);
    }

}
