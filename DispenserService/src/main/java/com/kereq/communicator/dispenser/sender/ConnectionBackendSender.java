package com.kereq.communicator.dispenser.sender;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.common.constant.RoutingKey;
import com.kereq.communicator.common.dto.ConnectionEventDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConnectionBackendSender {

    private final RabbitTemplate rabbitTemplate;

    public ConnectionBackendSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(ConnectionEventDTO connectionEvent) {
        rabbitTemplate.convertAndSend(ExchangeName.CONNECTIONS, RoutingKey.BACKEND, connectionEvent);
    }
}
