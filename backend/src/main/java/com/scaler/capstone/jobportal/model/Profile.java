package com.scaler.capstone.jobportal.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a user's profile on the job portal, including personal and professional information.")
public class Profile {
    @Schema(description = "Unique identifier of the profile")
    private Long id;

    @Schema(description = "Full name of the user")
    private String name;

    @Schema(description = "Email address of the user")
    private String email;

    @Schema(description = "Current job title of the user")
    private String jobTitle;

    @Schema(description = "Current company of the user")
    private String company;

    @Schema(description = "Location of the user")
    private String location;

    @Schema(description = "About or bio of the user")
    private String about;

    @Schema(description = "Profile picture as a byte array")
    private byte[] picture;

    @Schema(description = "Total experience in years")
    private Long totalExp;

    @Schema(description = "List of skills the user possesses")
    private List<String> skills;

    @Schema(description = "List of past experiences")
    private List<Experience> experiences;

    @Schema(description = "List of certifications achieved")
    private List<Certification> certifications;

    @Schema(description = "List of job IDs saved by the user")
    private List<Long> savedJobs;
}
