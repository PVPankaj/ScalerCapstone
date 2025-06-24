package com.scaler.capstone.jobportal.repository;

import com.scaler.capstone.jobportal.enums.NotificationStatus;
import com.scaler.capstone.jobportal.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
    public List<Notification> findByUserIdAndStatus(Long userId, NotificationStatus status);
}
