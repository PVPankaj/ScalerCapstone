package com.scaler.capstone.jobportal.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import com.scaler.capstone.jobportal.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Represents an applicant applying for a job through the job portal.
 * Contains applicant details like name, contact info, resume, cover letter,
 * and application status.
 */
@Schema(description = "Represents a job applicant")
public class Applicant {
    /**
     * Unique identifier for the applicant.
     */
    @Schema(description = "Unique identifier for the applicant")
    private Long applicantId;

    /**
     * Full name of the applicant.
     */
    @NotBlank(message = "Applicant name must not be blank")
    @Schema(description = "Full name of the applicant")
    private String name;

    /**
     * Email address of the applicant.
     */
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be blank")
    @Schema(description = "Email address of the applicant")
    private String email;

    /**
     * Phone number of the applicant.
     */
    @NotNull(message = "Phone number is required")
    @Digits(integer = 15, fraction = 0, message = "Phone number must be numeric and up to 15 digits")
    @Schema(description = "Phone number of the applicant")
    private Long phone;

    /**
     * Personal or portfolio website of the applicant.
     */
    @URL(message = "Website must be a valid URL")
    @Schema(description = "Personal or portfolio website of the applicant")
    private String website;

    /**
     * Resume file of the applicant as a byte array.
     */
    @NotNull(message = "Resume must be provided")
    @Schema(description = "Resume file of the applicant as a byte array")
    private byte[] resume;

    /**
     * Cover letter written by the applicant.
     */
    @Size(max = 5000, message = "Cover letter must not exceed 5000 characters")
    @Schema(description = "Cover letter written by the applicant")
    private String coverLetter;

    /**
     * Timestamp when the application was submitted.
     */
    @Schema(description = "Timestamp when the application was submitted")
    private LocalDateTime timestamp;

    /**
     * Current status of the application.
     */
    @NotNull(message = "Application status must be set")
    @Schema(description = "Current status of the application")
    private ApplicationStatus applicationStatus;

    /**
     * Scheduled interview time for the applicant.
     */
    @Schema(description = "Scheduled interview time for the applicant")
    private LocalDateTime interviewTime;
}
