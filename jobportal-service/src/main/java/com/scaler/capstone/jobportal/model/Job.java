package com.scaler.capstone.jobportal.model;

import java.time.LocalDateTime;
import java.util.List;

import com.scaler.capstone.jobportal.enums.JobType;
import io.swagger.v3.oas.annotations.media.Schema;

import com.scaler.capstone.jobportal.enums.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "jobs")
@Schema(description = "Represents a job listing with all relevant information for applicants")
public class Job {

    @Id
    @Schema(description = "Unique identifier of the job")
    private Long id;

    @Schema(description = "Title of the job position")
    private String jobTitle;

    @Schema(description = "Name of the company offering the job")
    private String company;

    @Schema(description = "List of applicants who have applied for the job")
    private List<Applicant> applicants;

    @Schema(description = "Information about the job or company")
    private String about;

    @Schema(description = "Required experience for the job role")
    private String experience;

    @Schema(description = "Type of job such as Full-time, Part-time, Contract")
    private JobType jobType;

    @Schema(description = "Job location")
    private String location;

    @Schema(description = "Salary or package offered for the position")
    private Long packageOffered;

    @Schema(description = "Date and time when the job was posted")
    private LocalDateTime postTime;

    @Schema(description = "Detailed description of the job role and responsibilities")
    private String description;

    @Schema(description = "Skills required for the job")
    private List<String> skillsRequired;

    @Schema(description = "Current status of the job such as OPEN, CLOSED")
    private JobStatus jobStatus;

    @Schema(description = "User ID of the job poster")
    private Long postedBy;
}
