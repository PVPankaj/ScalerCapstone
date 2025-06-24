package com.scaler.capstone.jobportal.dto.request;


import com.scaler.capstone.jobportal.enums.ApplicationStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantRequestDTO {

    private Long applicantId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Phone number is required")
    @Min(value = 1000000000L, message = "Phone number should be at least 10 digits")
    private Long phone;

    @URL(message = "Website should be a valid URL")
    private String website;

    @NotBlank(message = "Resume is required")
    private String resume;

    @NotBlank(message = "Cover letter is required")
    private String coverLetter;

    private LocalDateTime timestamp;

    @NotNull(message = "Application status is required")
    private ApplicationStatus applicationStatus;

    @Future(message = "Interview time must be in the future")
    private LocalDateTime interviewTime;
}
