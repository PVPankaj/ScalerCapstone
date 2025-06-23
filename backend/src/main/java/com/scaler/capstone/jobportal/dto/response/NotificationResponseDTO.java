package com.scaler.capstone.jobportal.dto.response;

import com.scaler.capstone.jobportal.enums.NotificationStatus;
import com.scaler.capstone.jobportal.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDTO {
    private Long id;
    private Long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus status;
    private LocalDateTime timestamp;

    public Notification toEntity() {
        return new Notification(this.id, this.userId, this.message, this.action, this.route, this.status, this.timestamp);
    }
}
