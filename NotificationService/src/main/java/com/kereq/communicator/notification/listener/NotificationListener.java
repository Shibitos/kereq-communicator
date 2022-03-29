package com.kereq.communicator.notification.listener;

import com.kereq.communicator.common.constant.QueueName;
import com.kereq.communicator.notification.entity.NotificationData;
import com.kereq.communicator.notification.sender.NotificationWebSocketSender;
import com.kereq.communicator.notification.service.NotificationStorageService;
import com.kereq.communicator.shared.dto.NotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

    private final NotificationStorageService notificationStorageService;

    private final NotificationWebSocketSender notificationWebSocketSender;

    public NotificationListener(NotificationStorageService messageStorageService, NotificationWebSocketSender messageWebsocketSender) {
        this.notificationStorageService = messageStorageService;
        this.notificationWebSocketSender = messageWebsocketSender;
    }

    @RabbitListener(queues = QueueName.NOTIFICATIONS_NOTIFICATION)
    public void onMessage(@Payload NotificationDTO notificationDTO) {
        if (notificationDTO.getUuid() == null) {
            throw new NullPointerException("Empty notification UUID");
        }
        if (notificationStorageService.existsByUuid(notificationDTO.getUuid())) {
            return;
        }
        NotificationData notification = new NotificationData(
                notificationDTO.getUuid(), notificationDTO.getSourceUserId(), notificationDTO.getRecipientId(),
                notificationDTO.getTitle(), notificationDTO.getContent(), notificationDTO.getDate());
        notification = notificationStorageService.storeNotification(notification);
        NotificationDTO newNotificationDTO = new NotificationDTO(
                notification.getId(), notification.getSourceUserId(), notification.getRecipientId(),
                notification.getTitle(), notification.getContent(), notification.getDate(), notification.isRead());
        notificationWebSocketSender.send(newNotificationDTO);
    }
}
