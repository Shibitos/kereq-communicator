package com.kereq.websocket.listener;

import com.kereq.communicator.common.dto.ConnectionEventDTO;
import com.kereq.communicator.common.dto.UserDTO;
import com.kereq.websocket.sender.ConnectionEventSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebSocketEventListener {

    private final ConnectionEventSender connectionEventSender;

    private final String instanceId;

    public WebSocketEventListener(ConnectionEventSender connectionEventSender,
                                  @Value("${eureka.instance.metadataMap.instanceId}") String instanceId) {
        this.connectionEventSender = connectionEventSender;
        this.instanceId = instanceId;
    }

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        UserDTO user = (UserDTO) event.getUser();
        if (user == null) {
            log.error("Connection empty user");
            throw new RuntimeException(); //TODO: cust except
        }
        log.info("New connection from {}", user.getId());
        ConnectionEventDTO connectionEvent = new ConnectionEventDTO(ConnectionEventDTO.Type.CONNECT, user.getId(), instanceId);
        connectionEventSender.send(connectionEvent);
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        UserDTO user = (UserDTO) event.getUser();
        if (user == null) {
            log.error("Disconnection empty user");
            throw new RuntimeException(); //TODO: cust except
        }
        log.info("New disconnection from {}", user.getId());
        ConnectionEventDTO connectionEvent = new ConnectionEventDTO(ConnectionEventDTO.Type.DISCONNECT, user.getId(), instanceId);
        connectionEventSender.send(connectionEvent);
    }
}
