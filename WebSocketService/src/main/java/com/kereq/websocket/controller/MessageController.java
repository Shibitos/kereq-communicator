package com.kereq.websocket.controller;

import com.kereq.communicator.common.dto.MessageDTO;
import com.kereq.communicator.common.dto.UserDTO;
import com.kereq.communicator.common.event.AbstractMessageEvent;
import com.kereq.websocket.dto.MessageEventDTO;
import com.kereq.websocket.sender.MessageChatSender;
import com.kereq.websocket.sender.MessageEventSender;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {

    private final MessageChatSender messageSender;

    private final MessageEventSender messageEventSender;

    public MessageController(MessageChatSender messageSender, MessageEventSender messageEventSender) {
        this.messageSender = messageSender;
        this.messageEventSender = messageEventSender;
    }

    @MessageMapping("/messages-outbox")
    public void greeting(@Payload MessageDTO messageDTO, Principal principal) {
        UserDTO user = (UserDTO) principal;
        MessageDTO message = new MessageDTO(user.getId(), messageDTO.getRecipientId(), messageDTO.getContent());
        messageSender.send(message);
    }

    @MessageMapping("/messages-event")
    public void greeting(@Payload MessageEventDTO messageEventDTO, Principal principal) {
        UserDTO user = (UserDTO) principal;
        AbstractMessageEvent messageEvent = messageEventDTO.toMessageEvent(user.getId());
        messageEventSender.send(messageEvent);
    }
}
