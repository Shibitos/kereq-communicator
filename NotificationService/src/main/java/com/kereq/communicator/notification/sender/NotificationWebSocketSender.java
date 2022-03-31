package com.kereq.communicator.notification.sender;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.shared.dto.NotificationDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationWebSocketSender {

    private final RabbitTemplate rabbitTemplate;

    public NotificationWebSocketSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(NotificationDTO notification) {
        rabbitTemplate.convertAndSend(ExchangeName.NOTIFICATIONS_WEBSOCKET, "", notification);
    }
}
