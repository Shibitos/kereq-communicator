package com.kereq.websocket.listener;

import com.kereq.communicator.common.constant.QueueName;
import com.kereq.communicator.shared.dto.ConnectionEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConnectionEventListener {

    @RabbitListener(queues = QueueName.CONNECTIONS_WEBSOCKET)
    public void onMessage(@Payload ConnectionEventDTO connectionEvent) {
        log.info("New connection event! {}", connectionEvent.getRecipientId());
    }
}
