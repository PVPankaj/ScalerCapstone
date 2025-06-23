package com.scaler.capstone.jobportal.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Schema(description = "Represents a One-Time Password (OTP) record used for verifying a user's email")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "otp")
public class OTP {
    @Schema(description = "Email address associated with the OTP")
    private String email;

    @Schema(description = "The one-time password code")
    private String otpCode;

    @Schema(description = "Time when the OTP was created")
    private LocalDateTime creationTime;
}
