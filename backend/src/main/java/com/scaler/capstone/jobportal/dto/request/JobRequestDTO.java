package com.scaler.capstone.jobportal.dto.request;

import com.scaler.capstone.jobportal.enums.JobStatus;
import com.scaler.capstone.jobportal.model.Job;
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
	private String jobTitle;
	private String company;
	private List<ApplicantRequestDTO> applicants;
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

	public Job toEntity() {
		return new Job(this.id, this.jobTitle, this.company, this.applicants!=null?this.applicants.stream().map((x)->x.toEntity()).toList():null,this.about, this.experience, this.jobType,
				this.location, this.packageOffered, this.postTime, this.description, this.skillsRequired,
				this.jobStatus, this.postedBy);
	}
}
