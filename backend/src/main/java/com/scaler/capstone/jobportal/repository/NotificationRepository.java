package com.scaler.capstone.jobportal.repository;

import com.scaler.capstone.jobportal.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
}
