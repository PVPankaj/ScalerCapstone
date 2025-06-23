package com.scaler.capstone.jobportal.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents an applicant's work experience, including job title, duration, and role description.")
public class Experience {
    @Schema(description = "Job title held by the applicant")
    private String title;

    @Schema(description = "Name of the company where the applicant worked")
    private String company;

    @Schema(description = "Location of the job")
    private String location;

    @Schema(description = "Start date of the job")
    private LocalDateTime startDate;

    @Schema(description = "End date of the job")
    private LocalDateTime endDate;

    @Schema(description = "Indicates if the applicant is currently working in this role")
    private Boolean working;

    @Schema(description = "Detailed description of the job role and responsibilities")
    private String description;
}
