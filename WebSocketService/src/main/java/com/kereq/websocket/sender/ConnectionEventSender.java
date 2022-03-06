package com.kereq.websocket.sender;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.common.constant.RoutingKey;
import com.kereq.communicator.shared.dto.ConnectionEventDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConnectionEventSender {

    private final RabbitTemplate rabbitTemplate;

    public ConnectionEventSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(ConnectionEventDTO connectionEvent) {
        rabbitTemplate.convertAndSend(ExchangeName.CONNECTIONS, RoutingKey.COMMUNICATOR, connectionEvent);
    }
}
