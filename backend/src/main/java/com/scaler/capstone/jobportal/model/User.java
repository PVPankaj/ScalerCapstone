package com.scaler.capstone.jobportal.model;

import com.scaler.capstone.jobportal.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a registered user in the job portal system")
public class User {
	@Schema(description = "Unique identifier of the user")
	private Long id;

	@Schema(description = "Name of the user")
	private String name;

	@Schema(description = "Email address of the user")
	private String email;

	@Schema(description = "Encrypted password of the user")
	private String password;

	@Schema(description = "Type of account - applicant or recruiter")
	private AccountType accountType;

	@Schema(description = "Profile ID linked to this user")
	private Long profileId;
}
