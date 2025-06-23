package com.scaler.capstone.jobportal.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.scaler.capstone.jobportal.enums.JobStatus;
import com.scaler.capstone.jobportal.model.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String jobType;
	private String location;
	private Long packageOffered;
	private LocalDateTime postTime;
	private String description;
	private List<String> skillsRequired;
	private JobStatus jobStatus;
	private Long postedBy;
}
