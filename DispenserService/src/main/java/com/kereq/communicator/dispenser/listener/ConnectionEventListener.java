package com.kereq.communicator.dispenser.listener;

import com.kereq.communicator.common.constant.QueueName;
import com.kereq.communicator.common.dto.ConnectionEventDTO;
import com.kereq.communicator.dispenser.service.ConnectionHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConnectionEventListener {

    private final ConnectionHandlerService connectionHandlerService;

    public ConnectionEventListener(ConnectionHandlerService connectionHandlerService) {
        this.connectionHandlerService = connectionHandlerService;
    }

    @RabbitListener(queues = QueueName.CONNECTIONS)
    public void onMessage(@Payload ConnectionEventDTO connectionEvent) {
        if (ConnectionEventDTO.Type.CONNECT.equals(connectionEvent.getType())) {
            connectionHandlerService.handleConnection(connectionEvent);
        } else if (ConnectionEventDTO.Type.DISCONNECT.equals(connectionEvent.getType())) {
            connectionHandlerService.handleDisconnection(connectionEvent);
        } else {
            log.error("Handler not found for connection type {}", connectionEvent.getType());
        }
    }
}
