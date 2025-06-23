package com.scaler.capstone.jobportal.model;


import java.time.LocalDateTime;

import com.scaler.capstone.jobportal.enums.NotificationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notification")
@Schema(description = "Represents a notification sent to a user in the job portal system")
public class Notification {
	@Schema(description = "Unique identifier of the notification")
	private Long id;

	@Schema(description = "ID of the user to whom the notification is sent")
	private Long userId;

	@Schema(description = "Notification message content")
	private String message;

	@Schema(description = "Optional action to be performed for the notification")
	private String action;

	@Schema(description = "Route or URL the notification points to")
	private String route;

	@Schema(description = "Current status of the notification")
	private NotificationStatus status;

	@Schema(description = "Timestamp when the notification was created")
	private LocalDateTime timestamp;
}
