package com.scaler.capstone.jobportal.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="profiles")
@Schema(description = "Represents a user's profile on the job portal, including personal and professional information.")
public class Profile {
    @Id
    @Schema(description = "Unique identifier of the profile")
    private Long id;

    @NotBlank(message = "User name must not be blank")
    @Schema(description = "Full name of the user")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be blank")
    @Schema(description = "Email address of the user")
    private String email;

    @NotBlank(message = "Job title must not be blank")
    @Schema(description = "Current job title of the user")
    private String jobTitle;

    @NotBlank(message = "Company name must not be blank")
    @Schema(description = "Current company of the user")
    private String company;

    @NotBlank(message = "Location must not be blank")
    @Schema(description = "Location of the user")
    private String location;

    @NotBlank(message = "About section must not be blank")
    @Schema(description = "About or bio of the user")
    private String about;

    @Schema(description = "Profile picture as a byte array")
    private byte[] picture;

    @NotNull(message = "Total experience must be provided")
    @Schema(description = "Total experience in years")
    private Long totalExp;

    @NotEmpty(message = "At least one skill is required")
    @Schema(description = "List of skills the user possesses")
    private List<String> skills;

    @Schema(description = "List of past experiences")
    private List<Experience> experiences;

    @Schema(description = "List of certifications achieved")
    private List<Certification> certifications;

    @Schema(description = "List of job IDs saved by the user")
    private List<Long> savedJobs;
}
