package com.kereq.websocket.sender;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketSender {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketSender(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendToUser(long userId, String destination, Object payload) {
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(userId), destination, payload);
    }
}
