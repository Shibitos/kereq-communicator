package com.kereq.communicator.notification.service;

import com.kereq.communicator.notification.entity.NotificationData;
import com.kereq.communicator.notification.repository.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationStorageService {

    private final NotificationRepository notificationRepository;

    public NotificationStorageService(NotificationRepository messageRepository) {
        this.notificationRepository = messageRepository;
    }

    public NotificationData storeNotification(NotificationData notification) {
        return notificationRepository.save(notification);
    }

    public boolean markNotificationRead(String messageId, long recipientId) {
        return notificationRepository.markRead(messageId, recipientId);
    }

    public Page<NotificationData> getNotificationsFor(long recipientId, Pageable page) {
        return notificationRepository.findByRecipientId(recipientId, page);
    }
}
