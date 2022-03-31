package com.kereq.websocket.sender;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.common.event.AbstractNotificationEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationEventSender {

    private final RabbitTemplate rabbitTemplate;

    public NotificationEventSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(AbstractNotificationEvent notificationEvent) {
        rabbitTemplate.convertAndSend(ExchangeName.EVENTS_NOTIFICATION_NOTIFICATION, "", notificationEvent);
    }
}
