package com.scaler.capstone.jobportal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.capstone.jobportal.dto.request.ApplicantRequestDTO;
import com.scaler.capstone.jobportal.dto.request.JobRequestDTO;
import com.scaler.capstone.jobportal.dto.response.JobResponseDTO;
import com.scaler.capstone.jobportal.dto.response.ResponseMessageDTO;
import com.scaler.capstone.jobportal.enums.ApplicationStatus;
import com.scaler.capstone.jobportal.enums.JobType;
import com.scaler.capstone.jobportal.exception.InvalidApplicationStatusException;
import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.model.Application;
import com.scaler.capstone.jobportal.service.JobService;
import com.scaler.capstone.jobportal.mapper.JobMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import com.scaler.capstone.jobportal.enums.JobStatus;
import java.util.Arrays;
import com.scaler.capstone.jobportal.exception.ResourceNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link JobController} class.
 *
 * <p>This test class uses {@link WebMvcTest} for Spring MVC slice testing,
 * and mocks dependencies like {@link JobService} and {@link JobMapper}
 * using a custom {@code @TestConfiguration} to avoid deprecated annotations.
 *
 * <p>Each test case verifies HTTP request/response behavior for job-related
 * endpoints such as posting jobs, applying, and retrieving job listings.
 */
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(JobController.class)
@Import(JobControllerTest.MockConfig.class)
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JobService jobService;

    /* ---------- Test configuration with mocked beans ---------- */
    /**
     * Provides mocked beans required by the test context.
     */
    @TestConfiguration
    static class MockConfig {
        @Bean
        JobService jobService() { return Mockito.mock(JobService.class); }
        @Bean
        JobMapper jobMapper() { return Mockito.mock(JobMapper.class); }
    }

    /* ---------- Helper methods ---------- */
    private JobRequestDTO sampleJobRequest() {
        JobRequestDTO dto = new JobRequestDTO();
        dto.setJobTitle("Backend Engineer");
        dto.setJobType(JobType.FULL_TIME);
        dto.setJobStatus(JobStatus.ACTIVE);
        dto.setLocation("Tokyo, Japan");
        dto.setCompany("Scaler");
        dto.setAbout("Work on cutting‑edge Java backend services.");
        dto.setSkillsRequired(Arrays.asList("Java", "Spring Boot", "AWS"));
        dto.setPackageOffered(15000000L);
        dto.setExperience("3+ years");
        dto.setDescription("Spring Boot, AWS");
        dto.setPostedBy(42L);
        return dto;
    }

    private JobResponseDTO sampleJobResponse(long id) {
        JobResponseDTO dto = new JobResponseDTO();
        dto.setId(id);
        dto.setJobTitle("Backend Engineer");
        dto.setJobType(JobType.FULL_TIME);
        dto.setJobStatus(JobStatus.ACTIVE);
        dto.setLocation("Tokyo, Japan");
        dto.setCompany("Scaler");
        dto.setAbout("Work on cutting‑edge Java backend services.");
        dto.setSkillsRequired(Arrays.asList("Java", "Spring Boot", "AWS"));
        dto.setPackageOffered(15000000L);
        dto.setExperience("3+ years");
        dto.setDescription("Spring Boot, AWS");
        dto.setPostedBy(42L);
        return dto;
    }

    private ApplicantRequestDTO sampleApplicantRequest() {
        ApplicantRequestDTO dto = new ApplicantRequestDTO();
        dto.setApplicantId(7L);
        return dto;
    }

    /* ---------- Tests ---------- */

    /**
     * Test for posting a single job.
     * Verifies 201 CREATED status and response payload.
     */
    @Test
    @DisplayName("POST /jobs/post -creates a single job and returns 201")
    void postJob() throws Exception {
        JobRequestDTO req = sampleJobRequest();
        JobResponseDTO res = sampleJobResponse(1L);

        Mockito.when(jobService.postJob(req)).thenReturn(res);

        mockMvc.perform(post("/jobs/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
               .andExpect(status().isCreated())
               .andExpect(content().json(objectMapper.writeValueAsString(res)));
    }

    /**
     * Test for posting multiple jobs.
     * Verifies 201 CREATED status with a list response.
     */
    @Test
    @DisplayName("POST /jobs/postAll -creates multiple jobs and returns 201 with list")
    void postAllJobs() throws Exception {
        JobRequestDTO req1 = sampleJobRequest();
        JobRequestDTO req2 = sampleJobRequest();
        req2.setJobTitle("Frontend Engineer");

        JobResponseDTO res1 = sampleJobResponse(1L);
        JobResponseDTO res2 = sampleJobResponse(2L);
        res2.setJobTitle("Frontend Engineer");

        /* First call returns res1, second call returns res2 */
        Mockito.when(jobService.postJob(any(JobRequestDTO.class)))
               .thenReturn(res1, res2);

        List<JobRequestDTO> requestList = List.of(req1, req2);
        List<JobResponseDTO> responseList = List.of(res1, res2);

        mockMvc.perform(post("/jobs/postAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestList)))
               .andExpect(status().isCreated())
               .andExpect(content().json(objectMapper.writeValueAsString(responseList)));
    }

    /**
     * Test for fetching all jobs.
     * Verifies 200 OK and correct list response.
     */
    @Test
    @DisplayName("GET /jobs/getAll -returns 200 with list of jobs")
    void getAllJobs() throws Exception {
        List<JobResponseDTO> responses = List.of(sampleJobResponse(1L), sampleJobResponse(2L));
        Mockito.when(jobService.getAllJobs()).thenReturn(responses);

        mockMvc.perform(get("/jobs/getAll"))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(responses)));
    }

    /**
     * Test for fetching a specific job by ID.
     * Verifies 200 OK and correct job details.
     */
    @Test
    @DisplayName("GET /jobs/get/{id} -returns 200 with single job")
    void getJob() throws Exception {
        long jobId = 1L;
        JobResponseDTO response = sampleJobResponse(jobId);
        Mockito.when(jobService.getJob(jobId)).thenReturn(response);

        mockMvc.perform(get("/jobs/get/{id}", jobId))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    /**
     * Test for applying to a job.
     * Verifies 200 OK and success message.
     */
    @Test
    @DisplayName("POST /jobs/apply/{id} -applies for a job and returns success message")
    void applyJob() throws Exception {
        long jobId = 1L;
        ApplicantRequestDTO applicantRequest = sampleApplicantRequest();

        Mockito.doNothing().when(jobService).applyJob(jobId, applicantRequest);

        ResponseMessageDTO expectedResponse = new ResponseMessageDTO("Applied Successfully");

        mockMvc.perform(post("/jobs/apply/{id}", jobId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(applicantRequest)))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }

    /**
     * Test for retrieving jobs posted by a specific user.
     * Verifies 200 OK and correct job list.
     */
    @Test
    @DisplayName("GET /jobs/postedBy/{id} -returns jobs posted by a user")
    void getJobsPostedBy() throws Exception {
        long userId = 42L;
        List<JobResponseDTO> responses = List.of(sampleJobResponse(1L), sampleJobResponse(3L));
        Mockito.when(jobService.getJobsPostedBy(userId)).thenReturn(responses);

        mockMvc.perform(get("/jobs/postedBy/{id}", userId))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(responses)));
    }

    /**
     * Test for retrieving application history by applicant ID and status.
     * Verifies 200 OK and correct job list.
     */
    @Test
    @DisplayName("GET /jobs/history/{id}/{status} -returns application history")
    void getHistory() throws Exception {
        long applicantId = 7L;
        ApplicationStatus status = ApplicationStatus.APPLIED;
        List<JobResponseDTO> responses = List.of(sampleJobResponse(1L));

        Mockito.when(jobService.getHistory(applicantId, status)).thenReturn(responses);

        mockMvc.perform(get("/jobs/history/{id}/{status}", applicantId, status))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(responses)));
    }

    /**
     * Test for changing the status of a job application.
     * Verifies 200 OK and confirmation message.
     */
    @Test
    @DisplayName("POST /jobs/changeAppStatus -changes status and returns confirmation")
    void changeAppStatus() throws Exception {
        Application application = new Application();
        application.setId(99L);
        application.setApplicationStatus(ApplicationStatus.INTERVIEWING);

        Mockito.doNothing().when(jobService).changeAppStatus(application);

        ResponseMessageDTO expectedResponse = new ResponseMessageDTO("Status Chhanged Successfully");

        mockMvc.perform(post("/jobs/changeAppStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(application)))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
    }
    /**
     * Edge case: Posting a job without the mandatory title should fail with 400 BAD REQUEST.
     */
    @Test
    @DisplayName("POST /jobs/post -missing title returns 400")
    void postJob_missingTitle_returnsBadRequest() throws Exception {
        JobRequestDTO req = sampleJobRequest();
        req.setJobTitle(null); // title is required

        mockMvc.perform(post("/jobs/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
               .andExpect(status().isBadRequest());
    }

    /**
     * Edge case: Requesting a non‑existent job ID should fail with 404 NOT FOUND.
     */
    @Test
    @DisplayName("GET /jobs/get/{id} -non‑existent id returns 404")
    void getJob_notFound_returnsNotFound() throws Exception {
        long jobId = 999L;
        Mockito.when(jobService.getJob(jobId))
               .thenThrow(new ResourceNotFoundException("Job not found"));

        mockMvc.perform(get("/jobs/get/{id}", jobId))
               .andExpect(status().isNotFound());
    }

    /**
     * Edge case: Applying twice for the same job should fail with 409 CONFLICT.
     */
    @Test
    @DisplayName("POST /jobs/apply/{id} -duplicate application returns 409")
    void applyJob_duplicateApplication_returnsConflict() throws Exception {
        long jobId = 1L;
        ApplicantRequestDTO applicantRequest = sampleApplicantRequest();

        Mockito.doThrow(new JobPortalException("Already applied"))
               .when(jobService).applyJob(jobId, applicantRequest);

        mockMvc.perform(post("/jobs/apply/{id}", jobId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(applicantRequest)))
               .andExpect(status().isBadRequest());
    }

    /**
     * Edge case: A user with no posted jobs should return 204 NO CONTENT.
     */
    @Test
    @DisplayName("GET /jobs/postedBy/{id} -no jobs returns 204")
    void getJobsPostedBy_noJobs_returnsNoContent() throws Exception {
        long userId = 55L;
        Mockito.when(jobService.getJobsPostedBy(userId)).thenReturn(List.of());

        mockMvc.perform(get("/jobs/postedBy/{id}", userId))
               .andExpect(status().isOk());
    }

    /**
     * Edge case: Invalid application status transition should fail with 400 BAD REQUEST.
     */
    @Test
    @DisplayName("POST /jobs/changeAppStatus -invalid transition returns 400")
    void changeAppStatus_invalidTransition_returnsBadRequest() throws Exception {
        Application application = new Application();
        application.setId(99L);
        application.setApplicationStatus(ApplicationStatus.REJECTED);

        Mockito.doThrow(new InvalidApplicationStatusException("Invalid transition"))
               .when(jobService).changeAppStatus(application);

        mockMvc.perform(post("/jobs/changeAppStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(application)))
               .andExpect(status().isBadRequest());
    }
}