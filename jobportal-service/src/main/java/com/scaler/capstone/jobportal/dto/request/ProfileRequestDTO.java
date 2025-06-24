package com.scaler.capstone.jobportal.dto.request;

import com.scaler.capstone.jobportal.model.Certification;
import com.scaler.capstone.jobportal.model.Experience;
import com.scaler.capstone.jobportal.model.Profile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "About section is required")
    private String about;

    @NotBlank(message = "Profile picture is required")
    private String picture;

    @NotNull(message = "Total experience is required")
    private Long totalExp;

    @NotEmpty(message = "Skills list cannot be empty")
    private List<String> skills;

    private Long id;

    private List<Experience> experiences;

    private List<Certification> certifications;

    private List<Long> savedJobs;
}
