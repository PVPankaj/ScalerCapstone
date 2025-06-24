package com.scaler.capstone.jobportal.service;

import com.scaler.capstone.jobportal.dto.request.NotificationRequestDTO;
import com.scaler.capstone.jobportal.enums.NotificationStatus;
import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.mapper.NotificationMapper;
import com.scaler.capstone.jobportal.model.Notification;
import com.scaler.capstone.jobportal.repository.NotificationRepository;
import com.scaler.capstone.jobportal.util.MongoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation for managing notifications in the job portal.
 */
@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * Sends a notification by saving it to the repository.
     *
     * @param notificationDTO the notification data to send
     * @throws JobPortalException if an error occurs while sending the notification
     */
    @Override
    public void sendNotification(NotificationRequestDTO notificationDTO) throws JobPortalException {
        notificationDTO.setId(MongoUtil.getNextSequenceId("notification"));
        notificationDTO.setStatus(NotificationStatus.UNREAD);
        notificationDTO.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notificationMapper.toModel(notificationDTO));
    }

    /**
     * Retrieves all unread notifications for the specified user.
     *
     * @param userId the ID of the user
     * @return a list of unread notifications
     */
    @Override
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    /**
     * Marks a notification as read based on its ID.
     *
     * @param id the ID of the notification to mark as read
     * @throws JobPortalException if the notification is not found
     */
    @Override
    public void readNotification(Long id) throws JobPortalException {
        Notification noti = notificationRepository.findById(id).orElseThrow(() -> new JobPortalException("No Notitication found"));
        noti.setStatus(NotificationStatus.READ);
        notificationRepository.save(noti);
    }
}
