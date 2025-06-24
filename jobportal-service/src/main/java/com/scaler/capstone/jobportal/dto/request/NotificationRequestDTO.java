package com.scaler.capstone.jobportal.dto.request;

import com.scaler.capstone.jobportal.enums.NotificationStatus;
import com.scaler.capstone.jobportal.model.Notification;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Message is required")
    private String message;

    @NotBlank(message = "Action is required")
    private String action;

    @NotBlank(message = "Route is required")
    private String route;

    @NotNull(message = "Notification status is required")
    private NotificationStatus status;

    @PastOrPresent(message = "Timestamp must be in the past or present")
    private LocalDateTime timestamp;
}
