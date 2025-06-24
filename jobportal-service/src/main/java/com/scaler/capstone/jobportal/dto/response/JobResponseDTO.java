package com.scaler.capstone.jobportal.dto.response;

import com.scaler.capstone.jobportal.enums.JobStatus;
import com.scaler.capstone.jobportal.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDTO {
    private Long id;
    private String jobTitle;
    private String company;
    private List<ApplicantResponseDTO> applicants;
    private String about;
    private String experience;
    private JobType jobType;
    private String location;
    private Long packageOffered;
    private LocalDateTime postTime;
    private String description;
    private List<String> skillsRequired;
    private JobStatus jobStatus;
    private Long postedBy;
}
