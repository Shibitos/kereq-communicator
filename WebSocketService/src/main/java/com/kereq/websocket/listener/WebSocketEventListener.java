package com.kereq.websocket.listener;

import com.kereq.common.constant.QueueName;
import com.kereq.common.dto.ConnectionEventDTO;
import com.kereq.common.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebSocketEventListener {

    private final RabbitTemplate rabbitTemplate;

    public WebSocketEventListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        UserDTO user = (UserDTO) event.getUser();
        if (user == null) {
            throw new RuntimeException(); //TODO: cust except
        }
        log.info("New connection! {}", user.getId());
        ConnectionEventDTO connectionEvent = new ConnectionEventDTO(ConnectionEventDTO.Type.CONNECT, user.getId());
        rabbitTemplate.convertAndSend(QueueName.CONNECTIONS, connectionEvent);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        UserDTO user = (UserDTO) event.getUser();
        if (user == null) {
            throw new RuntimeException(); //TODO: cust except
        }
        log.info("New disconnection! {}", user.getId());
        ConnectionEventDTO connectionEvent = new ConnectionEventDTO(ConnectionEventDTO.Type.DISCONNECT, user.getId());
        rabbitTemplate.convertAndSend(QueueName.CONNECTIONS, connectionEvent);
    }
}
