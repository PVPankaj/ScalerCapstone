package com.scaler.capstone.jobportal.service;

import com.scaler.capstone.jobportal.dto.request.NotificationRequestDTO;
import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.model.Notification;

import java.util.List;

public interface NotificationService {

    void sendNotification(NotificationRequestDTO notificationRequestDTO) throws JobPortalException;

    List<Notification> getUnreadNotifications(Long userId);

    void readNotification(Long id) throws JobPortalException;
}
