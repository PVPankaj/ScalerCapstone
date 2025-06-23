package com.scaler.capstone.jobportal.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a professional certification achieved by the applicant")
public class Certification {
	@Schema(description = "Name of the certification")
	private String name;
    @Schema(description = "Name of the issuing organization")
    private String issuer;
    @Schema(description = "Date when the certification was issued")
    private LocalDateTime issueDate;
    @Schema(description = "Unique identifier of the certification")
    private String certificateId;
}
