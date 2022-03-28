package com.kereq.communicator.notification.listener;

import com.kereq.communicator.common.constant.QueueName;
import com.kereq.communicator.common.event.AbstractNotificationEvent;
import com.kereq.communicator.common.event.ReadNotificationEvent;
import com.kereq.communicator.notification.service.NotificationStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationEventListener {

    private final NotificationStorageService notificationStorageService;

    public NotificationEventListener(NotificationStorageService notificationStorageService) {
        this.notificationStorageService = notificationStorageService;
    }

    @RabbitListener(queues = QueueName.EVENTS_NOTIFICATION_NOTIFICATION)
    public void onNotification(@Payload AbstractNotificationEvent notificationEvent) {
        if (notificationEvent instanceof ReadNotificationEvent) { //TODO: handlers
            if (!notificationStorageService.markNotificationRead(notificationEvent.getNotificationId(), notificationEvent.getSenderId())) {
                log.error("Error while marking notification {} read", notificationEvent.getNotificationId());
            }
        }
    }
}
