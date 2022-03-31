package com.kereq.websocket.controller;

import com.kereq.communicator.common.dto.UserDTO;
import com.kereq.communicator.common.event.AbstractNotificationEvent;
import com.kereq.websocket.dto.NotificationEventDTO;
import com.kereq.websocket.sender.NotificationEventSender;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class NotificationController {

    private final NotificationEventSender notificationEventSender;

    public NotificationController(NotificationEventSender notificationEventSender) {
        this.notificationEventSender = notificationEventSender;
    }

    @MessageMapping("/notifications-event")
    public void greeting(@Payload NotificationEventDTO notificationEventDTO, Principal principal) {
        UserDTO user = (UserDTO) principal;
        AbstractNotificationEvent notificationEvent = notificationEventDTO.toNotificationEvent(user.getId());
        notificationEventSender.send(notificationEvent);
    }
}
