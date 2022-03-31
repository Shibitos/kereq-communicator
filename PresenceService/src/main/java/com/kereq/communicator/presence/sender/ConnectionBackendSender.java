package com.kereq.communicator.presence.sender;

import com.kereq.communicator.common.constant.ExchangeName;
import com.kereq.communicator.shared.dto.ConnectionEventDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConnectionBackendSender {

    private final RabbitTemplate rabbitTemplate;

    public ConnectionBackendSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(ConnectionEventDTO connectionEvent) {
        rabbitTemplate.convertAndSend(ExchangeName.CONNECTIONS_BACKEND, "", connectionEvent);
    }
}
