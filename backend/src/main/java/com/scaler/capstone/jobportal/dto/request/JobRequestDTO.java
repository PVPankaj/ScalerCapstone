package com.scaler.capstone.jobportal.dto.request;

import com.scaler.capstone.jobportal.enums.JobStatus;
import com.scaler.capstone.jobportal.enums.JobType;
import com.scaler.capstone.jobportal.model.Job;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequestDTO {
    private Long id;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Company name is required")
    private String company;

    private List<ApplicantRequestDTO> applicants;

    @NotBlank(message = "About section is required")
    private String about;

    @NotBlank(message = "Experience is required")
    private String experience;

    @NotNull(message = "Job type is required")
    private JobType jobType;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Package offered is required")
    private Long packageOffered;

    @PastOrPresent(message = "Post time cannot be in the future")
    private LocalDateTime postTime;

    @NotBlank(message = "Description is required")
    private String description;

    @NotEmpty(message = "Skills required must not be empty")
    private List<String> skillsRequired;

    @NotNull(message = "Job status is required")
    private JobStatus jobStatus;

    @NotNull(message = "PostedBy is required")
    private Long postedBy;
}
