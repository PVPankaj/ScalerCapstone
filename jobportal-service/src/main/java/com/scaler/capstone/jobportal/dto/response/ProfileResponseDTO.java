package com.scaler.capstone.jobportal.dto.response;

import java.util.Base64;
import java.util.List;

import com.scaler.capstone.jobportal.model.Certification;
import com.scaler.capstone.jobportal.model.Experience;
import com.scaler.capstone.jobportal.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {
	private Long id;
	private String name;
	private String email;
	private String jobTitle;
	private String company;
	private String location;
	private String about;
	private String picture;
	private Long totalExp;
	private List<String> skills;
	private List<Experience>experiences;
	private List<Certification>certifications;
	private List<Long>savedJobs;
}
