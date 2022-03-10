package com.kereq.websocket.controller;

import com.kereq.communicator.common.dto.MessageDTO;
import com.kereq.communicator.common.dto.UserDTO;
import com.kereq.websocket.sender.MessageChatSender;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {

    private final MessageChatSender messageSender;

    public MessageController(MessageChatSender messageSender) {
        this.messageSender = messageSender;
    }

    @MessageMapping("/messages-outbox")
    public void greeting(@Payload MessageDTO message, Principal principal) {
        UserDTO user = (UserDTO) principal;
        MessageDTO newMessage = new MessageDTO(user.getId(), message.getRecipientId(), message.getContent());
        messageSender.send(newMessage);
    }
}
