package com.kereq.websocket.listener;

import com.kereq.communicator.common.constant.QueueName;
import com.kereq.communicator.shared.dto.NotificationDTO;
import com.kereq.websocket.sender.WebSocketSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

    private final WebSocketSender webSocketSender;

    public NotificationListener(WebSocketSender webSocketSender) {
        this.webSocketSender = webSocketSender;
    }

    @RabbitListener(queues = QueueName.NOTIFICATIONS_WEBSOCKET)
    public void onNotification(@Payload NotificationDTO notificationDTO) {
        log.info("Sending WS notification (recip: {})", notificationDTO.getRecipientId());
        webSocketSender.sendToUser(notificationDTO.getRecipientId(), "/queue/notifications-inbox", notificationDTO);
    }
}
